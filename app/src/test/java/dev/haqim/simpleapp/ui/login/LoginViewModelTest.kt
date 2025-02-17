package dev.haqim.simpleapp.ui.login


import app.cash.turbine.test
import dev.haqim.simpleapp.MainCoroutineRule
import dev.haqim.simpleapp.data.mechanism.Resource
import dev.haqim.simpleapp.domain.model.dummyUser
import dev.haqim.simpleapp.domain.usecase.ILoginUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var loginUseCase: ILoginUseCase

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setup() {
        loginViewModel = LoginViewModel(loginUseCase)
    }

    @Test
    fun `Initial state Should be empty`() = runTest {
        val initialState = loginViewModel.state
        assertNull(initialState.value.email)
        assertNull(initialState.value.password)
        assert(initialState.value.result is Resource.Idle)
    }

    @Test
    fun `When SetEmailAction Should update email`() = runTest {
        val email = "test@example.com"
        loginViewModel.doAction(LoginAction.SetEmail(email))
        val state = loginViewModel.state
        assertEquals(email, state.value.email)
    }

    @Test
    fun `When SetPasswordAction Should update password`() = runTest {
        val password = "password123"
        loginViewModel.doAction(LoginAction.SetPassword(password))
        val state = loginViewModel.state
        assertEquals(password, state.value.password)
    }

    @Test
    fun `When LoginAction Should call loginUseCase`() = runTest {
        val email = "test@example.com"
        val password = "password123"
        val user = dummyUser
        whenever(loginUseCase.process(email, password)).thenReturn(
            flow {
                emit(Resource.Loading())
                delay(100)
                Resource.Success(user)
            }
        )

        loginViewModel.doAction(LoginAction.SetEmail(email))
        loginViewModel.doAction(LoginAction.SetPassword(password))
        loginViewModel.doAction(LoginAction.Login)

        loginViewModel.state.test {
            verify(loginUseCase).process(email, password)
            cancelAndIgnoreRemainingEvents()
        }

    }

    @Test
    fun `When LoginAction Should return user`() = runTest {
        val email = "test@example.com"
        val password = "password123"
        val user = dummyUser
        whenever(loginUseCase.process(email, password)).thenReturn(
            flowOf(Resource.Success(user))
        )

        loginViewModel.doAction(LoginAction.SetEmail(email))
        loginViewModel.doAction(LoginAction.SetPassword(password))
        loginViewModel.doAction(LoginAction.Login)

        loginViewModel.state.test {
            awaitItem() // loading
            assertEquals(user, awaitItem().result.data)
        }
    }

    @Test
    fun `When LoginAction Should not return user`() = runTest {
        val email = ""
        val password = "password123"
        val user = dummyUser
        whenever(loginUseCase.process(email, password)).thenReturn(
            flow {
                emit(Resource.Loading())
                delay(100)
                Resource.Success(user)
            }
        )

        loginViewModel.doAction(LoginAction.SetEmail(email))
        loginViewModel.doAction(LoginAction.SetPassword(password))
        loginViewModel.doAction(LoginAction.Login)

        loginViewModel.state.test {
            // Initial state
            val initialState = awaitItem()
            assertEquals(initialState.email, email)
            assertEquals(initialState.password, password)
            assert(initialState.result is Resource.Idle)

            expectNoEvents()
        }
    }
}
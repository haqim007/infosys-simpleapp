package dev.haqim.simpleapp.ui.profile

// ProfileViewModelTest.kt

import app.cash.turbine.test
import dev.haqim.simpleapp.MainCoroutineRule
import dev.haqim.simpleapp.domain.model.dummyUser
import dev.haqim.simpleapp.domain.usecase.IGetUserUseCase
import dev.haqim.simpleapp.domain.usecase.ILogoutUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProfileViewModelTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var getUserUseCase: IGetUserUseCase

    @Mock
    private lateinit var logoutUseCase: ILogoutUseCase

    private lateinit var profileViewModel: ProfileViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        profileViewModel = ProfileViewModel(getUserUseCase, logoutUseCase)
    }

    @Test
    fun testInitialState() = runTest {
        val initialState = profileViewModel.state
        assertEquals(ProfileState(), initialState.value)
    }

    @Test
    fun testLoadUserAction_success() = runTest {
        whenever(getUserUseCase.process()).thenReturn(flow {
            delay(100)
            emit(dummyUser)
        })

        profileViewModel.doAction(ProfileAction.LoadUser)

        profileViewModel.state.test {
            verify(getUserUseCase).process()
            awaitItem()
            val expected = awaitItem()
            assertEquals(dummyUser, expected.user)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun testLogoutAction() = runTest {

        whenever(logoutUseCase.process()).thenReturn(flow {
            delay(100)
            emit(true)
        })

        profileViewModel.doAction(ProfileAction.Logout)

        verify(logoutUseCase).process()
    }
}
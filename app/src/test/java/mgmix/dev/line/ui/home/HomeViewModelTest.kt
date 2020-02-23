package mgmix.dev.line.ui.home

import junit.framework.Assert.assertEquals
import mgmix.dev.line.repository.LineRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Before
    fun setUp() {

    }

    @Test
    suspend fun getNoteList() {
        val repository = Mockito.mock(LineRepository::class.java)

        Mockito.`when`(repository.getAllNotes())

    }

    @Test
    fun getNotes() {
        
    }
}
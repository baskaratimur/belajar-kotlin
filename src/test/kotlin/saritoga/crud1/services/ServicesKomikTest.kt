package saritoga.crud1.service

import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import saritoga.crud1.model.ModelKomik
import saritoga.crud1.repository.RepositoryKomik
import java.util.*

class ServicesKomikTest {

    private lateinit var repo: RepositoryKomik
    private lateinit var service: ServicesKomik

    @BeforeEach
    fun setup() {
        repo = mockk()
        service = ServicesKomik(repo)
    }

    @Test
    fun `findAll should return list of komik`() {
        val list = listOf(
            ModelKomik("1", "Naruto", "Ninja", "Masashi"),
            ModelKomik("2", "One Piece", "Bajak laut", "Oda")
        )

        every { repo.findAll() } returns list

        val result = service.findAll()

        assertEquals(2, result.size)
        assertEquals("Naruto", result[0].title)
        verify { repo.findAll() }
    }

    @Test
    fun `findId should return komik when exists`() {
        val komik = ModelKomik("1", "Bleach", "Shinigami", "Kubo")
        every { repo.findById("1") } returns Optional.of(komik)

        val result = service.findId("1")

        assertNotNull(result)
        assertEquals("Bleach", result?.title)
        verify { repo.findById("1") }
    }

    @Test
    fun `update should merge and save correctly`() {
        val old = ModelKomik("1", "Bleach", "Lama", "Kubo")
        val update = ModelKomik("1", null, "Baru", null)

        every { repo.findById("1") } returns Optional.of(old)
        every { repo.save(any()) } answers { firstArg() }

        val result = service.update("1", update)

        assertEquals("Bleach", result?.title)         // tetap
        assertEquals("Baru", result?.description)     // berubah
        assertEquals("Kubo", result?.penulis)         // tetap

        verify { repo.save(match { it.description == "Baru" && it.title == "Bleach" }) }
    }

    @Test
    fun `findId should return null if komik not found`() {
        // Arrange
        every { repo.findById("99") } returns Optional.empty()

        // Act
        val result = service.findId("99")

        // Assert
        assertNull(result)
    }


}

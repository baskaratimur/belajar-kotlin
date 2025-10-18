package saritoga.crud1.model
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

@Document(collection = "perpus-collect")
data class ModelPerpus(
    @Id val id:String = UUID.randomUUID().toString(),
    val buku: String?,
    val author: List<String>? = emptyList<String>(),
//    val author: Any?,
    val harga: Harga,
    val createdDate: LocalDateTime = LocalDateTime.now()
)
data class Harga(
    val hargaIndo:Int,
    val hargaLuar:Int
)

data class CreateRequestPerpus(
    val buku: String?,
    val author: List<String>? = emptyList<String>(),
    val harga: Harga
)

data class GetAllBook(
    val id :String,
    val buku :String?
)

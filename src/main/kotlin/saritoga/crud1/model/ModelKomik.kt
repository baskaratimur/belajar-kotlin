package saritoga.crud1.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "komik_collect")
data class ModelKomik(
    @Id val id: String = UUID.randomUUID().toString(),
    val title: String?,
    val description: String,
    val penulis: String?
)

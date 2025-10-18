package saritoga.crud1.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import saritoga.crud1.model.ModelPerpus

@Repository
interface RepositoryPerpus: MongoRepository<ModelPerpus, String> {
    fun findByBukuContainingIgnoreCase(buku:String) : List<ModelPerpus>
}
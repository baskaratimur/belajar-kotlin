package saritoga.crud1.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import saritoga.crud1.model.ModelKomik

@Repository
interface RepositoryKomik : MongoRepository<ModelKomik, String>
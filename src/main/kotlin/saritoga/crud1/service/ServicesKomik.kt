package saritoga.crud1.service

import org.springframework.stereotype.Service
import org.springframework.ui.Model
import saritoga.crud1.model.ModelKomik
import saritoga.crud1.repository.RepositoryKomik

@Service
open class ServicesKomik(private val repo: RepositoryKomik){
    open fun findAll(): List<ModelKomik> = repo.findAll()
    open fun findId(id: String): ModelKomik? = repo.findById(id).orElse(null)
    open fun save(komik: ModelKomik): ModelKomik = repo.save(komik)
    open fun delete(id: String) = repo.deleteById(id)
    open fun update(id: String, updated: ModelKomik): ModelKomik? {
        return if (repo.existsById(id)){
            repo.save(updated.copy(id=id))
        }else null
//        cara biar ke title kosong, ga ke null
//        val existing = repo.findById(id).orElse(null) ?: return null
//
//        val merged = existing.copy(
//            title = updated.title ?: existing.title,
//            description = updated.description.ifBlank { existing.description },
//            penulis = updated.penulis ?: existing.penulis
//        )
//
//        return repo.save(merged)
    }


}
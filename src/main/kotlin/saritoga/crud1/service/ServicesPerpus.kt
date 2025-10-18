package saritoga.crud1.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import saritoga.crud1.helper.EmptyDataException
import saritoga.crud1.model.CreateRequestPerpus
import saritoga.crud1.model.GetAllBook
import saritoga.crud1.model.ModelPerpus
import saritoga.crud1.repository.RepositoryPerpus

@Service
open class ServicesPerpus(private val repo: RepositoryPerpus) {
    open fun getAll(search:String?, sort:String?): List<ModelPerpus> {
        val data = if (!search.isNullOrEmpty()){
           val found = repo.findByBukuContainingIgnoreCase(search).sortedByDescending { it.createdDate }
//            println("yang di get= $data")
            if (found.isEmpty()) throw EmptyDataException("Data kosong")
            found
        }else {
            val allData = repo.findAll()
            if (allData.isEmpty()) throw EmptyDataException("Data kosong")
            allData
        }
        return when (sort?.uppercase()) {
            "ASC" -> data.sortedBy { it.createdDate }
            "DESC" -> data.sortedByDescending { it.createdDate }
            else -> data.sortedByDescending { it.createdDate }
        }
    }

    open fun getId(id: String): ModelPerpus {
        return repo.findById(id).orElseThrow{
            NoSuchElementException("Data Not Found")
        }
    }
    open fun create(requestPerpus: ModelPerpus): ModelPerpus = repo.save(requestPerpus)
    open fun update( id: String, request: ModelPerpus) : ModelPerpus {
        return repo.save(request)
    }
    fun delete(id: String): Any {
        repo.deleteById(id)
        return mapOf("Message" to "Data dengan $id berhasil dihapus")
    }
//        if (repo.existsById(id)){
//            return repo.deleteById((id))
//        }else{
//            throw NoSuchElementException("data dengan $id tidak ditemukan")
//        }

    open fun validateToken(token: String?) {
        if (token == null || token != "Board"){
            throw SecurityException("Unauthorized")
        }
    }

    open fun getAllBook(): ResponseEntity<List<GetAllBook>>{
       val data = repo.findAll().map { GetAllBook(it.id, it.buku) }.toMutableList()
//        diubah ke mutable supaya bisa di add, karena dia listOff
        data.add(GetAllBook("000", "Total semua buku ${data.size}"))
        if (data.isEmpty()){
            throw NoSuchElementException("Data ga ada")
        }
        return ResponseEntity.ok(data)

    }
}
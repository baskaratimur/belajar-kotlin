package saritoga.crud1.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import saritoga.crud1.model.CreateRequestPerpus
import saritoga.crud1.model.GetAllBook
import saritoga.crud1.model.ModelPerpus
import saritoga.crud1.service.ServicesPerpus

@RestController
@RequestMapping("/perpus")
class ControllerPerpus(private val servicesPerpus: ServicesPerpus) {
    @PostMapping
    open fun save(@RequestBody request: CreateRequestPerpus, @RequestHeader(value = "Authorization", required = false)authheader: String?): ResponseEntity<ModelPerpus> {
        servicesPerpus.validateToken(authheader)
        val model = ModelPerpus(
            buku = request.buku,
            author = request.author,
            harga = request.harga
        )
        val result = servicesPerpus.create(model)
        return ResponseEntity.ok(result)
    }
    @GetMapping
    open fun getAll(search:String?, sort:String?): List<ModelPerpus> = servicesPerpus.getAll(search, sort)

    @GetMapping("/{id}")
    open fun getId(@PathVariable id: String, @RequestHeader(value = "Authorization", required = false) authheader: String?): ResponseEntity<Any>  {
        servicesPerpus.validateToken(authheader)
        val data = servicesPerpus.getId(id)
        return ResponseEntity.ok(data)
    }

    @PutMapping("/{id}")
    open fun update(@RequestBody request: CreateRequestPerpus, @PathVariable id: String, @RequestHeader(value = "Authorization", required = false)authHeader:String?): ResponseEntity<Any> {
        val existing = servicesPerpus.getId(id)
        val request = ModelPerpus(
            id = existing.id,
            buku = request.buku ?: existing.buku,
            author = request.author ?: existing.author,
            harga = request.harga ?: existing.harga
        )
        servicesPerpus.validateToken(authHeader)
        val result = servicesPerpus.update(id, request)
        return ResponseEntity.ok(result)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String, @RequestHeader(value = "Authorization", required = false) authHeader: String?): ResponseEntity<Any>? {
        println("isi auth header: $authHeader")
        servicesPerpus.validateToken(authHeader)
        servicesPerpus.getId(id)
        val result = servicesPerpus.delete(id)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/buku")
    open fun getAllBook(): ResponseEntity<List<GetAllBook>> = servicesPerpus.getAllBook()
}
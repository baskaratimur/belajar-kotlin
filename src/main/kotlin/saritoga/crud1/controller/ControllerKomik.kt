package saritoga.crud1.controller

import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import saritoga.crud1.model.ModelKomik
import saritoga.crud1.service.ServicesKomik

@RestController
@RequestMapping("/komikbaru")
open  class ControllerKomik(private val servicesKomik: ServicesKomik){

        @GetMapping
        open fun getAll(): List<ModelKomik> = servicesKomik.findAll()

//        @GetMapping("/{id}")
//        fun getById(@PathVariable id: String): ResponseEntity<Any> {
//                val result = servicesKomik.findId(id)
//                return if (result != null)
//                        ResponseEntity.ok(result)
//                else
//                        ResponseEntity.status(404).body(mapOf("data" to "not found"))
//        }

        @GetMapping("/{id}")
        fun getById(@PathVariable id: String): ResponseEntity<Any> {
                val result = servicesKomik.findId(id)

                return if (result != null) {
                        ResponseEntity.ok(result)
                } else {
                        val errorBody = mapOf(
                                "status" to 404,
                                "message" to "Data komik dengan ID '$id' tidak ditemukan"
                        )
                        ResponseEntity.status(404).body(errorBody)
                }
        }


        @PostMapping
        open fun save(@RequestBody komik : ModelKomik): ModelKomik = servicesKomik.save(komik)

        @DeleteMapping("/{id}")
        open fun delete(@PathVariable id: String) = servicesKomik.delete(id)

        @PutMapping("/{id}")
        open fun update(@PathVariable id: String, @RequestBody komik: ModelKomik): ResponseEntity<ModelKomik> {
                val updatedKomik = servicesKomik.update(id, komik)
                return updatedKomik?.let {
                        ResponseEntity.ok(it)
                } ?: ResponseEntity.notFound().build()
        }

}
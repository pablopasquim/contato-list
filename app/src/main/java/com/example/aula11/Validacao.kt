package com.example.aula11

class Validacao {

    companion object{

        private val USER = "pablopasquim"
        private val PASSWORD = "123"

        fun validacao(usuario: String, senha: String): Boolean{

            if(usuario == USER &&
                senha == PASSWORD){
                return true
            }
            return false
        }

    }

}
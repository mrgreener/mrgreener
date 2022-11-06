package tech.mrgreener.application.utils

/*
 * Copyright 2020 Alexander Kovrigin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*

import com.auth0.jwt.interfaces.RSAKeyProvider

//import io.ktor.client.HttpClient
//import io.ktor.client.engine.apache.Apache
//import io.ktor.client.engine.cio.*
//import io.ktor.client.request.get
//import io.ktor.client.request.url
//import io.ktor.server.plugins.contentnegotiation.*
//import io.ktor.server.util.*
//import kotlinx.coroutines.runBlocking
import java.io.ByteArrayInputStream
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
//import io.ktor.client.plugins.contentnegotiation.*
//import io.ktor.serialization.gson.*


class MyRSAKeyProvider : RSAKeyProvider {
    /**
     * HttpClient
     */
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    override fun getPrivateKeyId(): String {
        throw NotImplementedError("MyRSAKeyProvider doesn't implement getPrivateKeyId")
    }

    override fun getPrivateKey(): RSAPrivateKey {
        throw NotImplementedError("MyRSAKeyProvider doesn't implement getPrivateKey")
    }

    override fun getPublicKeyById(kid: String?): RSAPublicKey = runBlocking {
        val keys = client.get("https://www.googleapis.com/robot/v1/metadata/x509/securetoken@system.gserviceaccount.com").body<Map<String, String>>()
//        val keys = client.get<Map<String, String>> {
//            url()
//        }
        val cert: String = keys[kid!!] ?: error("Matching certificate not found")
        val fact = CertificateFactory.getInstance("X.509")
        val stream = ByteArrayInputStream(cert.toByteArray())
        val certificate = fact.generateCertificate(stream) as X509Certificate
        return@runBlocking certificate.publicKey as RSAPublicKey
    }

}
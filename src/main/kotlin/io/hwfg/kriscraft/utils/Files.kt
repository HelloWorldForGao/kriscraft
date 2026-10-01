package io.hwfg.kriscraft.utils

import com.google.gson.reflect.TypeToken
import io.hwfg.kriscraft.Kriscraft
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.createFile
import kotlin.io.path.exists
import kotlin.io.path.readText
import kotlin.io.path.writeText

fun Path.safeCreate(){
    if (Files.exists(this)) return
    this.createFile()
}
fun Path.safeWrite(obj : Any?){
    when (obj) {
        is String -> {
            //Files.createDirectories(this.parent)
            this.parent.createDirectories()
            this.safeCreate()
            /*Files.write(
                this,
                obj.toByteArray()
            )*/
            this.writeText(obj)
        }

        null -> {
            this.safeWrite("")
        }

        else -> {
            val res = Kriscraft.gson.toJson(obj) ?: ""
            this.safeWrite(res)
        }
    }
}
fun Path.safeRead() : String{
    //Files.createDirectories(this.parent)
    this.parent.createDirectories()
    return if (this.exists()) this.readText()
    else {
        this.safeCreate()
        ""
    }
}
fun <A : Any>Path.safeRead(type : TypeToken<A>) : A?{
    val res = this.safeRead()
    return Kriscraft.gson.fromJson<A>(
        res,
        type
    )
}
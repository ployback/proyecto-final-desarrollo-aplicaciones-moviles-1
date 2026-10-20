package pe.edu.cibertec.final.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import pe.edu.cibertec.final.model.LenteApiModel

class LenteSqliteHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE $TABLE_LENTES (" +
                "$COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_NOMBRE TEXT, " +
                "$COLUMN_MARCA TEXT, " +
                "$COLUMN_PRECIO REAL, " +
                "$COLUMN_STOCK INTEGER, " +
                "$COLUMN_DESCRIPCION TEXT, " +
                "$COLUMN_IMAGEN_PATH TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_LENTES")
        onCreate(db)
    }

    fun getAll(): List<LenteApiModel> {
        val lentes = mutableListOf<LenteApiModel>()
        readableDatabase.query(
            TABLE_LENTES,
            null,
            null,
            null,
            null,
            null,
            "$COLUMN_ID ASC"
        ).use { cursor ->
            while (cursor.moveToNext()) {
                lentes.add(
                    LenteApiModel(
                        id = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                        nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE)),
                        marca = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MARCA)),
                        precio = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRECIO)),
                        stock = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_STOCK)),
                        descripcion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPCION)),
                        imagenPath = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGEN_PATH))
                    )
                )
            }
        }
        return lentes
    }

    fun saveAll(lentes: List<LenteApiModel>) {
        writableDatabase.beginTransaction()
        try {
            writableDatabase.delete(TABLE_LENTES, null, null)
            lentes.forEach { lente ->
                val values = ContentValues().apply {
                    put(COLUMN_ID, lente.id)
                    put(COLUMN_NOMBRE, lente.nombre)
                    put(COLUMN_MARCA, lente.marca)
                    put(COLUMN_PRECIO, lente.precio)
                    put(COLUMN_STOCK, lente.stock)
                    put(COLUMN_DESCRIPCION, lente.descripcion)
                    put(COLUMN_IMAGEN_PATH, lente.imagenPath)
                }
                writableDatabase.insertOrThrow(TABLE_LENTES, null, values)
            }
            writableDatabase.setTransactionSuccessful()
        } finally {
            writableDatabase.endTransaction()
        }
    }

    companion object {
        private const val DATABASE_NAME = "final_lentes_db"
        private const val DATABASE_VERSION = 1
        const val TABLE_LENTES = "lentes"
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_MARCA = "marca"
        const val COLUMN_PRECIO = "precio"
        const val COLUMN_STOCK = "stock"
        const val COLUMN_DESCRIPCION = "descripcion"
        const val COLUMN_IMAGEN_PATH = "imagen_path"
    }
}

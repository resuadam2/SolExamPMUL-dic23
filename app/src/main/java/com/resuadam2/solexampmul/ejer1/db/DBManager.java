package com.resuadam2.solexampmul.ejer1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Clase que gestiona la base de datos
 */
public class DBManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "albumes.db"; // Nombre de la base de datos

    private static final int DB_VERSION = 1; // Versión de la base de datos

    private static final String TABLE_ALBUMES = "albumes"; // Nombre de la tabla (albumes)

    private static final String COLUMN_ID = "id"; // Nombre de la columna (id)

    private static final String COLUMN_TITULO = "titulo"; // Nombre de la columna (titulo)

    private static final String COLUMN_ARTISTA = "artista"; // Nombre de la columna (artista)

    private static final String COLUMN_NUM_CANCIONES = "num_canciones";// Nombre de la columna (num_canciones)

    private static final String COLUMN_VALORACION = "valoracion"; // Nombre de la columna (valoracion)

    /**
     * Constructor de la clase
     *
     * @param context Contexto de la aplicación
     */
    public DBManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Método que se ejecuta al crear la base de datos
     * @param db Base de datos
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DBManager", "Creando la base de datos");
        // Crear la tabla
        try {
            db.beginTransaction();
            db.execSQL("CREATE TABLE " + TABLE_ALBUMES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITULO + " TEXT NOT NULL, " +
                    COLUMN_ARTISTA + " TEXT NOT NULL, " +
                    COLUMN_NUM_CANCIONES + " INTEGER NOT NULL, " +
                    COLUMN_VALORACION + " INTEGER NOT NULL);");
            db.execSQL("INSERT INTO " + TABLE_ALBUMES + " (" +
                    COLUMN_TITULO + ", " +
                    COLUMN_ARTISTA + ", " +
                    COLUMN_NUM_CANCIONES + ", " +
                    COLUMN_VALORACION + ") VALUES ('Me muevo con Dios', 'Cruz Cafune', 23, 5);");
            db.execSQL("INSERT INTO " + TABLE_ALBUMES + " (" +
                    COLUMN_TITULO + ", " +
                    COLUMN_ARTISTA + ", " +
                    COLUMN_NUM_CANCIONES + ", " +
                    COLUMN_VALORACION + ") VALUES ('For all the dogs', 'Drake', 23, 5);");
            db.execSQL("INSERT INTO " + TABLE_ALBUMES + " (" +
                    COLUMN_TITULO + ", " +
                    COLUMN_ARTISTA + ", " +
                    COLUMN_NUM_CANCIONES + ", " +
                    COLUMN_VALORACION + ") VALUES ('.MP3', 'Emilia', 13, 4);");
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("DBManager", "Error creando la base de datos" + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    /**
     * Método que se ejecuta al actualizar la base de datos
     * @param sqLiteDatabase Base de datos
     * @param i Versión anterior
     * @param i1 Versión nueva
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("DBManager", "Actualizando la base de datos");
        // Eliminar la tabla
        try {
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ALBUMES);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("DBManager", "Error actualizando la base de datos" + e.getMessage());
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    /**
     * Método para insertar un nuevo album
     * @param titulo Titulo del album
     * @param artista Artista del album
     * @param numCanciones Número de canciones del album
     * @param valoracion Valoración del album
     */
    public void insertarAlbum(String titulo, String artista, int numCanciones, int valoracion) {
        Log.i("DBManager", "Insertando un nuevo album");
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ALBUMES
                    + " WHERE " + COLUMN_TITULO + " = '" + titulo
                    + "' AND " + COLUMN_ARTISTA + " = '" + artista + "';", null);
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITULO, titulo);
            values.put(COLUMN_ARTISTA, artista);
            values.put(COLUMN_NUM_CANCIONES, numCanciones);
            values.put(COLUMN_VALORACION, valoracion);
            db.beginTransaction();
            if (cursor.getCount() > 0) {
                db.update(TABLE_ALBUMES, values, COLUMN_TITULO + " = ? AND " + COLUMN_ARTISTA + " = ?", new String[]{titulo, artista});
            } else {
                db.insert(TABLE_ALBUMES, null, values);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("DBManager", "Error insertando un nuevo album" + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    /**
     * Método para eliminar un album
     * @param titulo Titulo del album
     * @param artista Artista del album
     */
    public void eliminarAlbum(String titulo, String artista) {
        Log.i("DBManager", "Eliminando un album");
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            db.beginTransaction();
            db.delete(TABLE_ALBUMES, COLUMN_TITULO + " = ? AND " + COLUMN_ARTISTA + " = ?", new String[]{titulo, artista});
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("DBManager", "Error eliminando un album" + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    /**
     * Método para obtener todos los albumes
     * @return Cursor con todos los albumes
     */
    public Cursor getAlbumes() {
        Log.i("DBManager", "Obteniendo todos los albumes");
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM " + TABLE_ALBUMES, null);
        } catch (SQLException e) {
            Log.e("DBManager", "Error obteniendo todos los albumes" + e.getMessage());
        }
        return cursor;
    }

    /**
     * Método para obtener un album
     * @param titulo Titulo del album
     * @param artista Artista del album
     * @return Cursor con el album
     */
    public Cursor getAlbum(String titulo, String artista) {
        Log.i("DBManager", "Obteniendo un album");
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM " + TABLE_ALBUMES
                    + " WHERE " + COLUMN_TITULO + " = '" + titulo
                    + "' AND " + COLUMN_ARTISTA + " = '" + artista + "';", null);
        } catch (SQLException e) {
            Log.e("DBManager", "Error obteniendo un album" + e.getMessage());
        }
        return cursor;
    }

    /**
     * Método para obtener todos los albumes ordenados
     * @param columna Columna por la que ordenar
     * @param orden Orden (ASC o DESC)
     * @return Cursor con todos los albumes ordenados
     */
    public Cursor getAlbumesOrderBy(String columna, String orden) {
        Log.i("DBManager", "Obteniendo todos los albumes ordenados");
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = getReadableDatabase();
            if (columna == null || columna.isEmpty()) cursor = getAlbumes();
            else if (orden == null || orden.isEmpty())
                cursor = db.rawQuery("SELECT * FROM " + TABLE_ALBUMES
                        + " ORDER BY " + columna + ";", null);
            else if (orden.equalsIgnoreCase("ASC") || orden.equalsIgnoreCase("DESC"))
            cursor = db.rawQuery("SELECT * FROM " + TABLE_ALBUMES
                    + " ORDER BY " + columna + " " + orden + ";", null);
             else cursor = getAlbumes();
        } catch (SQLException e) {
            Log.e("DBManager", "Error obteniendo todos los albumes ordenados" + e.getMessage());
        } finally {
            db.endTransaction();
        }
        return cursor;
    }
}

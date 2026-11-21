package com.ute.studentmvvm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ute.studentmvvm.model.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Student::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: run {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "ute_student_room.db"
                    ).addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            CoroutineScope(Dispatchers.IO).launch {
                                INSTANCE?.studentDao()?.insertAll(
                                    listOf(
                                        Student(
                                            fullName = "Nguyễn Văn An",
                                            studentCode = "20110001",
                                            email = "an.nv@ute.udn.vn",
                                            gpa = 3.85
                                        ),
                                        Student(
                                            fullName = "Trần Thị Mai",
                                            studentCode = "20110002",
                                            email = "mai.tt@ute.udn.vn",
                                            gpa = 3.92
                                        ),
                                        Student(
                                            fullName = "Lê Hoàng Phúc",
                                            studentCode = "20110003",
                                            email = "phuc.lh@ute.udn.vn",
                                            gpa = 3.45
                                        ),
                                        Student(
                                            fullName = "Phạm Quốc Dũng",
                                            studentCode = "20110004",
                                            email = "dung.pq@ute.udn.vn",
                                            gpa = 3.60
                                        )
                                    )
                                )
                            }
                        }
                    }).build()

                    INSTANCE = instance
                    instance
                }
            }
        }
    }
}

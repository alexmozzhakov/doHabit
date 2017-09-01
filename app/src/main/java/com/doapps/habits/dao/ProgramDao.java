package com.doapps.habits.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.doapps.habits.models.Program;
import java.util.List;

@Dao
public interface ProgramDao {

  @Query("SELECT * FROM program")
  List<Program> getAll();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertAll(Program... programs);

  @Query("SELECT EXISTS(SELECT 1 FROM program WHERE id = :id LIMIT 1)")
  int idExists(int id);

  @Query("DELETE FROM program WHERE habit_id = :habitId")
  void delete(int habitId);
}
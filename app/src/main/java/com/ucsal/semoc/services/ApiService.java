package com.ucsal.semoc.services;

import com.ucsal.semoc.models.EntityModel;
import com.ucsal.semoc.models.LecturesModel;
import com.ucsal.semoc.models.MinicoursesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
  @GET("minicursos.json")
  Call<List<MinicoursesModel>> getCourses();

  @GET("palestras.json")
  Call<List<LecturesModel>> getLectures();

  @GET("pessoas.json")
  Call<List<EntityModel>> getEntities();

  @GET("pessoas/{id}.json")
  Call<EntityModel> getEntity(@Path("id") int id);
}

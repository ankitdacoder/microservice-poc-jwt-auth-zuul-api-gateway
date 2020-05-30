package com.webcoder.app.data;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.webcoder.app.model.AlbumReponseModel;

@FeignClient(name="albums-ws")
public interface AlbumServiceClient {

	@GetMapping("/users/{id}/albums")
	public List<AlbumReponseModel> getAlbums(@PathVariable String id);
}

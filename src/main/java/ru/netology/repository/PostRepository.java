package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PostRepository {
  private final ConcurrentHashMap<Long, Post> postList;
  private long postIdCounter;

  public PostRepository() {
    this.postList = new ConcurrentHashMap<>();
    this.postIdCounter = 0;
  }
  public List<Post> all() {
    return new ArrayList<>(postList.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(postList.get(id));
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      post.setId(++postIdCounter);
      postList.put(postIdCounter, post);
      return post;
    }
    if (postList.containsKey(post.getId())) {
      postList.get(post.getId()).setContent(post.getContent());
      return postList.get(post.getId());
    } else
      return null;
  }

  public boolean removeById(long id) {
    if (postList.containsKey(id)) {
      postList.remove(id);
      return true;
    }
    return false;
  }
}

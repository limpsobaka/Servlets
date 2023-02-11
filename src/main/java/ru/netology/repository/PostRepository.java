package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {
  private final ConcurrentHashMap<Long, Post> postList;
  private final AtomicLong postIdCounter = new AtomicLong();

  public PostRepository() {
    this.postList = new ConcurrentHashMap<>();
  }
  public List<Post> all() {
    return new ArrayList<>(postList.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(postList.get(id));
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      long id = postIdCounter.incrementAndGet();
      post.setId(id);
      postList.put(id, post);
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

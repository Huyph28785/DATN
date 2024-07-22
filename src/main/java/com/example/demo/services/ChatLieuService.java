package com.example.demo.services;

import com.example.demo.models.ChatLieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ChatLieuService {
    public Page<ChatLieu> getAll(Pageable pageable);

    public Page<ChatLieu> getAll1(Pageable pageable);

    public List<ChatLieu> findAll();

    public List<ChatLieu> findAllFullTT();

    public ChatLieu findById(UUID id);

    public ChatLieu add(ChatLieu chatLieu);

    public ChatLieu update(UUID id, ChatLieu chatLieu);

    public void updateTT();

    public List<ChatLieu> search0(String ten);

    public List<ChatLieu> search1(String ten);

    ChatLieu findByMa(String ma);

    ChatLieu findByTen(String ten);
}

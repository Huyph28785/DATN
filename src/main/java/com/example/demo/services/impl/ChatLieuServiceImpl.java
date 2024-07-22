package com.example.demo.services.impl;

import com.example.demo.models.ChatLieu;
import com.example.demo.repositories.ChatLieuRepository;
import com.example.demo.services.ChatLieuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatLieuServiceImpl implements ChatLieuService {

    @Autowired
    private ChatLieuRepository chatLieuRepository;

    @Override
    public Page<ChatLieu> getAll(Pageable pageable) {
        return chatLieuRepository.getAll(pageable);
    }

    @Override
    public Page<ChatLieu> getAll1(Pageable pageable) {
        return chatLieuRepository.getAll1(pageable);
    }

    @Override
    public List<ChatLieu> findAll() {
        return chatLieuRepository.findAll0();
    }

    @Override
    public List<ChatLieu> findAllFullTT() {
        return chatLieuRepository.findAll();
    }

    @Override
    public ChatLieu findById(UUID id) {
        return chatLieuRepository.findById(id).orElse(null);
    }

    @Override
    public ChatLieu add(ChatLieu chatLieu) {
        return chatLieuRepository.save(chatLieu);
    }

    @Override
    public ChatLieu update(UUID id,ChatLieu chatLieu) {
        if (id != null) {
            ChatLieu chatLieuUpdate = chatLieuRepository.findById(id).orElse(null);
            if (chatLieuUpdate != null) {
                BeanUtils.copyProperties(chatLieu, chatLieuUpdate);
                chatLieuRepository.save(chatLieuUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {

    }

    @Override
    public List<ChatLieu> search0(String ten) {
        return chatLieuRepository.search0(ten);
    }

    @Override
    public List<ChatLieu> search1(String ten) {
        return chatLieuRepository.search1(ten);
    }

    @Override
    public ChatLieu findByMa(String ma) {
        return chatLieuRepository.findChatLieuByMa(ma);
    }

    @Override
    public ChatLieu findByTen(String ten) {
        return chatLieuRepository.findChatLieuByTen(ten);
    }
}

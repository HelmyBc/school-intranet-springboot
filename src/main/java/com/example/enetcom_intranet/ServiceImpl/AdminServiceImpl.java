package com.example.enetcom_intranet.ServiceImpl;

import com.example.enetcom_intranet.model.Admin;
import com.example.enetcom_intranet.repository.AdminRepository;
import com.example.enetcom_intranet.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;


    @Override
    public List<Admin> getAdmins() {
        List<Admin> admins = new ArrayList<>();
        adminRepository.findAll().forEach(admins::add);
        return admins;
    }

    @Override
    public Admin getAdminById(Integer id) {
        return adminRepository.findById(id).get();
    }

    @Override
    public Admin insert(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public void updateAdmin(Integer id, Admin s) {
        Admin adminFromDb = adminRepository.findById(id).get();
        System.out.println(adminFromDb);
        adminFromDb.setCin(s.getCin());
        adminFromDb.setFirstName(s.getFirstName());
        adminFromDb.setLastName(s.getLastName());
        adminFromDb.setEmail(s.getEmail());
        adminFromDb.setPassword(s.getPassword());
        adminFromDb.setPhone(s.getPhone());
        adminFromDb.setImageUrl(s.getImageUrl());
        adminFromDb.setPostsId(s.getPostsId());
        adminRepository.save(adminFromDb);
    }

    @Override
    public void deleteAdmin(Integer id) {
        adminRepository.deleteById(id);
    }
}

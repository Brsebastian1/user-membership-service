package com.udea.usermembershipservice.aplication.port.in;


import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.udea.usermembershipservice.aplication.useCase.dto.mermberHome.MemberDto;
import com.udea.usermembershipservice.aplication.useCase.dto.mermberHome.MemberHomeDto;

public interface ICreatedMemberHome {

    public void createdMemberHome(String gmail, String rol, String nameHogar);
    public void deleteMemberHome(String nameHome, String gmail);
    public CompletableFuture<MemberHomeDto> getMemberHome(String gmail);
    public List<MemberDto> getAllMemberHome(String nameHome);
    public void updateRoleMemberHome(String nameHome, String gmail, String newRol);
}

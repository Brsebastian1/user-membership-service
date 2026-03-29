package com.udea.usermembershipservice.aplication.port.in;

import com.udea.usermembershipservice.aplication.useCase.dto.LoginDto;
import com.udea.usermembershipservice.aplication.useCase.dto.LoginResultDto;

public interface ILoginUserCase {
    
    public LoginResultDto login(LoginDto loginDto);
}

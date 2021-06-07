package com.cevher.ms.user.vm;

import com.cevher.ms.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTempVM {
    private User user;
    private DepartmentVM department;
}

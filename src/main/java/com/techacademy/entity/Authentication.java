package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.techacademy.validation.UnusedCode;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {

    //グループを作成
    public static interface Create {}
    public static interface Update {}

    /** 役割の列挙型 */
    public static enum Role {
        管理者, 一般
    }

    /** 社員番号 */
    @Id
    @Column(length = 20, nullable = false)
    @NotEmpty // 追加
    @Length(max=20) // 追加
    @UnusedCode(groups={ Create.class })
    private String code;

    /** パスワード */
    @Column(length = 255, nullable = false)
    @Length(min=8, max=64, groups={ Create.class }) // 追加
    @Pattern(regexp = "|.{8,64}",message="8 から 64 の間の長さにしてください")
    private String password;

    /** 権限 */
    @Enumerated(EnumType.STRING)
    @NotNull // 追加
    private Role role;

    /** ユーザID */
    @OneToOne
    @JoinColumn(name="employee_id", referencedColumnName="id")
    private Employee employee;
}
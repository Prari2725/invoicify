package Invoicify.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

@JsonInclude(Include.NON_DEFAULT)
public class Role {
private long id;

private String name;

private String permission;





}

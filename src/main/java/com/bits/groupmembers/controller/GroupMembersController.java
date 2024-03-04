package com.bits.groupmembers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bits.groupmembers.dto.GroupMembersInfoDto;
import com.bits.groupmembers.dto.StudentInfoDto;
import com.bits.groupmembers.service.GroupMembersService;

@RestController
@RequestMapping("/api/v1/bits/cloudcomputing")
public class GroupMembersController {
	
	@Autowired
	private GroupMembersService groupMembersService;
	
	@GetMapping("/all")
	public GroupMembersInfoDto getAllGroupMembersInformation() {
		return this.groupMembersService.getGroupMembersDetails();
	}

	@GetMapping("/groupmembers/{electives}")
	public List<StudentInfoDto> getAllGroupMembersForGivenElectives(@PathVariable("electives") String electives) {
		return this.groupMembersService.getAllGroupMembersForGivenElectives(electives);
	}

}

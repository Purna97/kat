// 
// Decompiled by Procyon v0.5.36
// 

package com.knowledgeassessment.DTO;

import java.util.LinkedList;
import java.util.List;

public class ScoreDto
{
    private List<NotAttendedReportDto> notAttendedDto;
    private List<ReportDto> reportDtoList;
    private List scoreCountList;
    private int userCount;
    
    public ScoreDto() {
        this.notAttendedDto = new LinkedList<NotAttendedReportDto>();
        this.reportDtoList = new LinkedList<ReportDto>();
        this.scoreCountList = new LinkedList();
    }
    
    public List<ReportDto> getReportDtoList() {
        return this.reportDtoList;
    }
    
    public void setReportDtoList(final List<ReportDto> reportDtoList) {
        this.reportDtoList = reportDtoList;
    }
    
    public List getScoreCountList() {
        return this.scoreCountList;
    }
    
    public void setScoreCountList(final List scoreCountList) {
        this.scoreCountList = scoreCountList;
    }
    
    public int getUserCount() {
        return this.userCount;
    }
    
    public void setUserCount(final int userCount) {
        this.userCount = userCount;
    }
    
    public List<NotAttendedReportDto> getNotAttendedDto() {
        return this.notAttendedDto;
    }
    
    public void setNotAttendedDto(final List<NotAttendedReportDto> notAttendedDto) {
        this.notAttendedDto = notAttendedDto;
    }
}

package com.kes.kote.domain;

public class StacsCourseCreditDomain {

	private String DisciplineName;
	
	private String ProgramName;
	
	private String CourseID;
	
	private String DepartmentID;
	
	private int Semester;
	
	private String SubjectID;
	
	private double SessionMax;
	
	private double TheoryMax;
	
	private double PracticalMax;
	
	private double SessionMin;
	
	private double TheoryMin;
	
	private double PracticalMin;

	private double CreditHours;
	
	public String getDisciplineName() {
		return DisciplineName;
	}

	public void setDisciplineName(String disciplineName) {
		DisciplineName = disciplineName;
	}

	public String getProgramName() {
		return ProgramName;
	}

	public void setProgramName(String programName) {
		ProgramName = programName;
	}

	public String getCourseID() {
		return CourseID;
	}

	public void setCourseID(String courseID) {
		CourseID = courseID;
	}

	public String getDepartmentID() {
		return DepartmentID;
	}

	public void setDepartmentID(String departmentID) {
		DepartmentID = departmentID;
	}

	public int getSemester() {
		return Semester;
	}

	public void setSemester(String semester) {
		try
		{
			if(semester.contains("Semester"))
			{
				semester=semester.substring(semester.indexOf("r")+1, semester.length());
			}
			if(semester!=null && semester.trim().length()>0)
				Semester = Integer.parseInt(semester.trim());
			else
				Semester =0;
		}catch(Exception ex){Semester =0;}
	}

	public String getSubjectID() {
		return SubjectID;
	}

	public void setSubjectID(String subjectID) {
		SubjectID = subjectID;
	}

	public  double getSessionMax() {
		return SessionMax;
	}

	public void setSessionMax(String sessionMax) {
		/*if(sessionMax!=null && sessionMax.trim().length()>0)
				if(sessionMax.contains("."))
					{
						Double dnt=Double.valueOf(sessionMax);
						sessionMax=""+dnt.intValue();
					}
			
					//sessionMax=sessionMax.substring(0, sessionMax.indexOf(".")+1);
			System.out.println(sessionMax);*/
			try{	SessionMax = Double.valueOf(sessionMax); }catch(Exception ex){}
		
	}

	public double getTheoryMax() {
		return TheoryMax;
	}

	public void setTheoryMax(String theoryMax) {
		/*if(theoryMax!=null && theoryMax.trim().length()>0)
			if(theoryMax.contains("."))
			{
				Double dnt=Double.valueOf(theoryMax);
				theoryMax=""+dnt.intValue();
			}*/
		try{	TheoryMax = Double.valueOf(theoryMax); }catch(Exception ex){}
			
	}

	public double getPracticalMax() {
		return PracticalMax;
	}

	public void setPracticalMax(String practicalMax) {
		/*if(practicalMax!=null && practicalMax.trim().length()>0)
			if(practicalMax.contains("."))
			{
				Double dnt=Double.valueOf(practicalMax);
				practicalMax=""+dnt.intValue();
			}*/
		try{	PracticalMax = Double.valueOf(practicalMax); }catch(Exception ex){}
			
	}

	public double getSessionMin() {
		return SessionMin;
	}

	public void setSessionMin(String sessionMin) {
		/*if(sessionMin!=null && sessionMin.trim().length()>0)
			if(sessionMin.contains("."))
			{
				Double dnt=Double.valueOf(sessionMin);
				sessionMin=""+dnt.intValue();
			}*/
		try{	SessionMin = Double.valueOf(sessionMin); }catch(Exception ex){}
			
	}

	public double getTheoryMin() {
		return TheoryMin;
	}

	public void setTheoryMin(String theoryMin) {
		/*if(theoryMin!=null && theoryMin.trim().length()>0)
			if(theoryMin.contains("."))
			{
				Double dnt=Double.valueOf(theoryMin);
				theoryMin=""+dnt.intValue();
			}*/
		try{	TheoryMin = Double.valueOf(theoryMin); }catch(Exception ex){}
			
	}

	public double getPracticalMin() {
		return PracticalMin;
	}

	public void setPracticalMin(String practicalMin) {
		/*if(practicalMin!=null && practicalMin.trim().length()>0)
			if(practicalMin.contains("."))
			{
				Double dnt=Double.valueOf(practicalMin);
				practicalMin=""+dnt.intValue();
			}*/
		try{	PracticalMin = Double.valueOf(practicalMin); }catch(Exception ex){}
			
	}

	
	public double getCreditHours() {
		return CreditHours;
	}

	public void setCreditHours(String creditHours) {
		try{ CreditHours =Double.valueOf(creditHours); }catch(Exception ex){}
	}

	
	@Override
	public String toString() {
		return "StacsCourseCreditDomain [DisciplineName=" + DisciplineName + ", ProgramName=" + ProgramName
				+ ", CourseID=" + CourseID + ", DepartmentID=" + DepartmentID + ", Semester=" + Semester
				+ ", SubjectID=" + SubjectID + ", SessionMax=" + SessionMax + ", TheoryMax=" + TheoryMax
				+ ", PracticalMax=" + PracticalMax + ", SessionMin=" + SessionMin + ", TheoryMin=" + TheoryMin
				+ ", PracticalMin=" + PracticalMin + ", CreditHours=" + CreditHours + "]";
	}

	public boolean isValid()
	{
		if(getDepartmentID()!=null && getDepartmentID().length()>0)
			if(getSubjectID()!=null && getSubjectID().length()>0)
				if(getSemester()>=0)
					if(getSessionMax()>0)
						if(getTheoryMax()>0)
							if(getPracticalMax()>0)
								if(getSessionMin()>0)
									if(getTheoryMin()>0)
										if(getPracticalMin()>0)
											if(getCreditHours()>0)
												return true;
		
		return false;
	}
	
	/*public static void main(String[] args) {
		
		StacsCourseCreditDomain row=new StacsCourseCreditDomain();
		row.setSessionMax("1");
		row.setTheoryMax("1");
		row.setPracticalMax("2.0");
		
		row.setSessionMax("1");
		row.setTheoryMax("1");
		row.setPracticalMax("2.0");
		
		row.setCreditHours("24.54");
		System.out.println(row.toString());
	}*/
}

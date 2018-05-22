package com.kds.KODE_DEV.dao;

public class Queries {
	
	/**
	public final static String getCourseForFaculty ="select distinct(a.course_id_defined_by_teaching_source), c.department_id, c.department_name from admin_faculty_mapping a, transaction_course_description_info b, transaction_department_info c  where "+
           "a.faculty_user_id = ? and "+
"b.course_id_declared_by_teaching_source = a.course_id_defined_by_teaching_source and c.department_id = b.course_id";

	*/
	
	public final static String getCourseForFaculty ="select distinct(course_id_defined_by_teaching_source) from admin_faculty_mapping where faculty_user_id =?";
	public final static String getSubjectForFacultyForAttendanceMarking ="select distinct(a.course_id_defined_by_teaching_source), c.department_name from admin_faculty_mapping a, mst_course_catalogue b, transaction_department_info c  where  a.faculty_user_id =? and a.organization_id =? and b.organization_id = a.organization_id and b.deptid_by_ts = a.course_id_defined_by_teaching_source and c.department_id = b.dept_id ";
	
	public final static String getSubjectForFaculty ="select distinct(a.faculty_subject_id), b.subname from admin_faculty_mapping a, subject_description b where "+ 
"a.faculty_user_id = ? and "+
"b.course_id_defined_by_teaching_source =? and "+
"b.subject_id = a.faculty_subject_id ";
	
	public final static String getStudentsForFacultyWithSubject ="select c.enrollment_process_id, c.full_name from admin_faculty_mapping a, transaction_course_description_info b, transaction_enrollment_process c where "+
     "a.faculty_user_id = ?";
	
	
	public final static String getCourseForStudent ="select distinct(a.course_id_defined_by_teaching_source),"
			+ " c.department_id, c.department_name from admin_faculty_mapping a, transaction_course_description_info b,"
			+ " transaction_department_info c  where c.department_id='deptid266' and "
			+ "b.course_id_declared_by_teaching_source = a.course_id_defined_by_teaching_source and c.department_id = b.course_id";


	
}

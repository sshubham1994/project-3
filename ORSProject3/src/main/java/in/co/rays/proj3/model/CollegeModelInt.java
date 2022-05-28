package in.co.rays.proj3.model;

import java.util.List;

import in.co.rays.proj3.dto.CollegeDTO;
import in.co.rays.proj3.exception.ApplicationException;
import in.co.rays.proj3.exception.DuplicateRecordException;



/**
 * Data Access Object of College
 * @author shubham sharma
 *
 */
public interface CollegeModelInt {

	/**
	 * Find College by PK
	 *
	 * @param pk : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public CollegeDTO findByPK(long pk) throws ApplicationException;

	/**
	 * Find College by Name
	 *
	 * @param name : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */
	public CollegeDTO findByName(String name) throws ApplicationException;

	/**
	 * Add a College
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException : throws when college already exists
	 */
	public long add(CollegeDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Update a College
	 *
	 * @param dto
	 * @throws ApplicationException
	 * @throws DuplicateRecordException : if updated user record is already exist
	 */
	public void update(CollegeDTO dto) throws ApplicationException, DuplicateRecordException;

	/**
	 * Delete a College
	 *
	 * @param dto
	 * @return
	 * @throws ApplicationException
	 */
	public void delete(CollegeDTO dto) throws ApplicationException;

	/**
	 * Search College with pagination
	 *
	 * @return list : List of College
	 * @param dto      : Search Parameters
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */
	public List search(CollegeDTO dto, int pageNo, int pageSize) throws ApplicationException;

	/**
	 * Search College
	 *
	 * @return list : List of College
	 * @param dto : Search Parameters
	 * @throws ApplicationException
	 */
	public List search(CollegeDTO dto) throws ApplicationException;

	/**
	 * get List of College with pagination
	 *
	 * @return list : List of College
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException;

	/**
	 * Gets List of College
	 *
	 * @return list : List of College
	 * @throws DatabaseException
	 */
	public List list() throws ApplicationException;

}

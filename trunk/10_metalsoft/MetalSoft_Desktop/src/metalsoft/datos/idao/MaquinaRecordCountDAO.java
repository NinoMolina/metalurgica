/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:03 ART 2010
*
* DAO-Generator Version 1.2
*
*/
package metalsoft.datos.idao;
import java.math.*;
import java.sql.*;
import java.net.URL;
import java.util.*;
import java.sql.Connection;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
public interface MaquinaRecordCountDAO
{
	public MaquinaRecordCount getRecordCount(Connection con) throws MaquinaRecordCountException;
}

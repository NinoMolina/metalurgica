/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:07 ART 2010
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
public interface ClienteRecordCountDAO
{
	public ClienteRecordCount getRecordCount(Connection con) throws ClienteRecordCountException;
}

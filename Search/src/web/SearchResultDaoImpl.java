package web;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;

public class SearchResultDaoImpl implements SearchResultDao{
	
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public SearchResult getSearchResultById(int id) {
		
		final int id_db = id;
		final SearchResult sr = new SearchResult();
		
		JdbcTemplate template = new JdbcTemplate(dataSource);
		
		template.query("select * from product where id=?",
				new PreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						// TODO Auto-generated method stub
						ps.setInt(1, id_db);
					}
				},new RowCallbackHandler() {
					
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						// TODO Auto-generated method stub
						try{
							sr.setContent(rs.getString("contents"));
							sr.setFilename(rs.getString("filename"));
							sr.setFullpath(rs.getString("fullpath"));
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				});
		return sr;
	}
	

}

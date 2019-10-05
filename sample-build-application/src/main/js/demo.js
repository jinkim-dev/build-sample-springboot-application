import React, { useState, useEffect } from 'react';
import MaterialTable from 'material-table';
import axios from 'axios';

export default function MaterialTableDemo() {
	const [state, setState] = React.useState({});
	  useEffect(() => {
	    axios.get('/api/builds')
	    .then(
	            result => {
	            	console.info(JSON.stringify(result));
	            	const data = result.data;
	            	const columns = [
	                  { title: 'ID', field: 'id' },
	                  { title: 'NAME', field: 'name' },
	                  { title: 'GIT ADDRESS', field: 'gitAddress'}
	                  ];
	            	setState({
	            		columns : columns,
	            		data : data
	            	});
	            }
	        );
	  }, []);

  return (
    <MaterialTable
      title="Editable Example"
      columns={state.columns}
      data={state.data}
      actions={[
        {
          icon: 'build',
          tooltip: 'Build App',
          onClick: (event, rowData) => alert("You built " + rowData.name)
        }
      ]}
    />
  );
}

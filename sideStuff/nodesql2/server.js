var node_mssql = require('node-mssql');

var db = require("./db");

/* add configuration to query object */
var queryObj = new node_mssql.Query({
    host: 'DESKTOP-7CKVB6Q',	 // You can use 'x.x.x.x\\instance' to connect to named instance
    port: 1433,
    username: 'dp2',
    password: 'ZevBev123',
    database: 'Class.'
});

var sql = "SELECT * FROM dbo.Students";

db.executeSql(sql, function (data, err) {
    if (err) {
        console.log(" Internal Error: error connecting Database", err);
    } else {
        console.log("success", data);
    }
});



// /* set table name to operate */
// queryObj.table('dbo.Students');

// /* set update query condition */
// queryObj.where({
//     'LastName': 'Smith',
// });

// /* run update query and fetch response */
// queryObj.select(function (results) {
//     //  success callback
//     console.log(results);
// }, function (err, sql) {
//     //  failed callback
//     if (err)
//         console.log(err);

//     console.log(sql);
// });
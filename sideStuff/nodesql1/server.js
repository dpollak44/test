var Connection = require('tedious').Connection;
var config = {
    server: 'DESKTOP-7CKVB6Q.Class.windows.net',  //update me
    authentication: {
        type: 'default',
        options: {
            userName: 'dpollak44', //update me
            password: 'ZevsBevs123'  //update me
        }
    },
    options: {
        // If you are on Microsoft Azure, you need encryption:
        instance: 'mssqlserver',
        encrypt: true,
        database: 'Class'  //update me
    }
};
var connection = new Connection(config);
connection.on('connect', function (err) {
    // If no error, then good to proceed.  
    console.log("Connected");
    executeStatement();
});

var Request = require('tedious').Request;
var TYPES = require('tedious').TYPES;

function executeStatement() {
    request = new Request("Select * from Students", function (err) {
        if (err) {
            console.log(err);
        }
    });
    var result = "";

    request.on(newFunction(), function (columns) {
        columns.forEach(function (column) {
            if (column.value === null) {
                console.log('NULL');
            } else {
                result += column.value + " ";
            }
        });
        console.log(result);
        result = "";
    });

    request.on('requestCompleted', function (rowCount, more) {
        console.log(rowCount + ' rows returned');
    });
    connection.execSql(request);

    function newFunction() {
        return 'requestCompleted';
    }
}  
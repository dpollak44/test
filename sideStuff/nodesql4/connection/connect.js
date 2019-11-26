var sql = require("mssql");
var connect = function () {
    var conn = new sql.ConnectionPool({
        user: 'dp2',
        password: 'ZevBev123',
        server: 'DESKTOP-7CKVB6Q',
        database: 'Class'
    });

    return conn;
};

module.exports = connect;
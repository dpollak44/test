// const config = {
//     user: 'dpollak44',
//     password: 'ZevsBevs123',
//     server: 'localhost', // You can use 'localhost\\instance' to connect to named instance
//     database: 'Class',
// }


// const sql = require('mssql')

// sql.on('error', err => {
//     // ... error handler
// })

// sql.connect(config).then(pool => {
//     // Query

//     return pool.request()

//         .query('select * from students')
// }).then(result => {
//     console.dir(result)
// }).catch(err => {
//     // ... error checks
// });

const sql = require("msnodesqlv8");

const connectionString = "server=DESKTOP-7CKVB6Q.;Database=Class;Trusted_Connection=Yes;Driver={SQL Server Native Client 11.0}";
const query = "SELECT * FROM Class.Students";

sql.query(connectionString, query, (err, rows) => {
    console.log(rows);
});

const sql = require('mssql/msnodesqlv8');

async () => {
    try {
        // make sure that any items are correctly URL encoded in the connection string
        await sql.connect('mssql://dp2:ZevBev123@localhost/Class');
        const result = await sql.query`select * from Students where LastName = Smith`;
        console.dir(result);
    } catch (err) {
        console.log('error');
    }
};
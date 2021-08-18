const fs = require('fs');
const faker = require('faker');

const ACTIVITY_COUNT = 500;
const TARIFF_COUNT = 10;
const DEVICE_COUNT = 50;
const USER_COUNT = 2200;
const BASE = '../db/';
let path, writer;

function handleFile(name) {
    path = BASE + name;
    try {
        fs.unlinkSync(path);
    } catch(e) {}
    writer = fs.createWriteStream(path, { flags: 'a' });
}

function getDate() {
    return new Date(faker.date.recent()).toISOString();
}


// Generate `populate_tariffs.sql`
handleFile('populate_tariff.sql');

Array(TARIFF_COUNT)
    .fill()
    .forEach((x, i) => {
        writer.write(
            'INSERT INTO tariff (title, minutes_outer, internet_traffic, sms_count) ' +
            `VALUES('Tariff #${i + 1}', ${100 + 20 * i}, ${200 + 20 * i}, ${10 + i * 10});\n`
        );
    });

writer.end();


// Generate `populate_devices.sql`
handleFile('populate_device.sql');

Array(DEVICE_COUNT)
    .fill()
    .forEach((x, i) => {
        const model = `${faker.lorem.word()} ${faker.datatype.number({ min: 1, max: 500, precision: 1 })}`;
        writer.write(
            `INSERT INTO device (model) VALUES('${model}');\n`
        );
    });

writer.end();


// Generate `populate_sms.sql`
handleFile('populate_sms.sql');

Array(ACTIVITY_COUNT)
    .fill()
    .forEach((x, i) => {
        const text = faker.lorem.sentence();
        const userId = faker.datatype.number({ min: 1, max: USER_COUNT, precision: 1 });
        const inter = faker.phone.phoneNumber();
        const created = getDate();
        writer.write(
            'INSERT INTO sms (text, user_id, interlocutor, created) ' +
            `VALUES('${text}', ${userId}, '${inter}', '${created}');\n`
        );
    });

writer.end();


// Generate `populate_call.sql`
handleFile('populate_call.sql');

Array(ACTIVITY_COUNT)
    .fill()
    .forEach((x, i) => {
        const duration = faker.datatype.number({ min: 10, max: 36000, precision: 1 });
        const userId = faker.datatype.number({ min: 1, max: USER_COUNT, precision: 1 });
        const inter = faker.phone.phoneNumber();
        const created = getDate();
        writer.write(
            'INSERT INTO call (duration, user_id, interlocutor, created) ' +
            `VALUES(${duration}, ${userId}, '${inter}', '${created}');\n`
        );
    });

writer.end();


// Generate `populate_traffic.sql`
handleFile('populate_traffic.sql');

Array(ACTIVITY_COUNT)
    .fill()
    .forEach((x, i) => {
        const traffic = faker.datatype.number({ min: 10, max: 30000000, precision: 1 });
        const userId = faker.datatype.number({ min: 1, max: USER_COUNT, precision: 1 });
        const created = getDate();
        writer.write(
            'INSERT INTO traffic (traffic, user_id, created) ' +
            `VALUES(${traffic}, ${userId}, '${created}');\n`
        );
    });

writer.end();


// Generate `populate_user.sql`
handleFile('populate_user.sql');

Array(USER_COUNT)
    .fill()
    .forEach((x, i) => {
        const firstName = faker.name.firstName().replace("'");
        const lastName = faker.name.lastName().replace("'");
        const tariff = faker.datatype.number({ min: 1, max: TARIFF_COUNT, precision: 1 });
        const device = faker.datatype.number({ min: 1, max: DEVICE_COUNT, precision: 1 });
        writer.write(
            'INSERT INTO "user" (first_name, last_name, tariff, device) ' +
            `VALUES('${firstName}', '${lastName}', ${tariff}, ${device});\n`
        );
    });

writer.end();

const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose'); // Assuming MongoDB database

const app = express();

// Connect to MongoDB database (replace connection string with yours)
mongoose.connect('mongodb://localhost:27017/student-management', {
  useNewUrlParser: true,
  useUnifiedTopology: true
})
.then(() => console.log('MongoDB connected'))
.catch(err => console.error(err));

// Define student schema for MongoDB
const studentSchema = new mongoose.Schema({
  name: String,
  email: String,
  password: String
});

const Student = mongoose.model('Student', studentSchema);

// Middleware to parse incoming request body as JSON
app.use(bodyParser.json());

// Signup API endpoint
app.post('/api/signup', async (req, res) => {
  const { name, email, password } = req.body;

  // Hash password before storing (implement hashing logic here)
  const hashedPassword = hashPassword(password);

  const newStudent = new Student({
    name,
    email,
    password: hashedPassword
  });

  try {
    const savedStudent = await newStudent.save();
    res.json({ message: 'Signup successful!', student: savedStudent });
  } catch (err) {
    res.status(500).json({ message: 'Error during signup!', error: err });
  }
});

// Implement other API endpoints for login, data retrieval, etc.

const port = process.env.PORT || 5

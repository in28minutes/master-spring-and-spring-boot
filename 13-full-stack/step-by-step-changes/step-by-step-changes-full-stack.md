# Section 01

## Building Java Full Stack Application with Spring Boot and React

### Step 15

- Step 01 - Getting Started - Full Stack Spring Boot and React Application
- Step 02 - Exploring What and Why of Full Stack Architectures
- Step 03 - Understanding JavaScript and EcmaScript History
- Step 04 - Installing Visual Studio Code
- Step 05 - Installing nodejs and npm
- Step 06 - Creating React App with Create React App
- Step 07 - Exploring Important nodejs Commands - Create React App
- Step 08 - Exploring Visual Studio Code and Create React App
- Step 09 - Exploring Create React App Folder Structure
- Step 10 - Getting started with React Components
- Step 11 - Creating Your First React Component and more
- Step 12 - Getting Started with State in React - useState hook
- Step 13 - Exploring JSX - React Views
- Step 14 - Following JavaScript Best Practices - Refactoring to Modules
- Step 15 - Exploring JavaScript further

#### /01-frontend/todo-app/src/App.css

```css
.App {
  text-align: center;
  font-size: 20px;
}

```
---

#### /01-frontend/todo-app/src/App.js

```js
import './App.css';
import LearningComponent from './components/learning-examples/LearningComponent'

function App() {
  return (
    <div className="App">
      <LearningComponent />
    </div>
  )
}

export default App;
```

---

#### /01-frontend/todo-app/src/components/learning-examples/FirstComponent.jsx

```
export default function FirstComponent() {
    return (
      <div className="FirstComponent">First Component</div>
    )
}

export function FifthComponent() {
    return (
      <div className="FifthComponent">Fifth Component</div>
    )
}
  
```
---

#### /01-frontend/todo-app/src/components/learning-examples/FourthComponent.jsx

```
import {Component} from 'react'

export default class FourthComponent extends Component{
  render() {
    return (
      <>
        <div className="FourthComponent">Fourth Component</div>
        <div className="FourthComponent">Fourth Component</div>
      </>
    )
  }
}
```
---

#### /01-frontend/todo-app/src/components/learning-examples/LearningComponent.jsx

```
import FirstComponent from './FirstComponent'
import {FifthComponent} from './FirstComponent'
import SecondComponent from './SecondComponent'
import ThirdComponent from './ThirdComponent'
import FourthComponent from './FourthComponent'
import LearningJavaScript from './LearningJavaScript'

export default function LearningComponent() {
    return (
      <div className="App">
        <FirstComponent />
        <SecondComponent />
        <ThirdComponent />
        <FourthComponent />
        <FifthComponent />
        <LearningJavaScript />
      </div>
    )
  }
  
```
---

#### /01-frontend/todo-app/src/components/learning-examples/LearningJavaScript.jsx

```
const person = {
    name: 'Ranga Karanam',
    address: {
        line1: 'Baker Street',
        city: 'London',
        country: 'UK',
    },
    profiles: ['twitter', 'linkedin', 'instagram'],
    printProfile: () => {
        person.profiles.map(
            profile => console.log(profile)
        )
    }
}

export default function LearningJavaScript(){
    return (
        <>
            <div>{person.name}</div>
            <div>{person.address.line1}</div>
            <div>{person.profiles[0]}</div>
            <div>{ person.printProfile() }</div>
        </>
    )
}
```
---

#### /01-frontend/todo-app/src/components/learning-examples/SecondComponent.jsx

```
export default function SecondComponent() {
  return (
    <div className="SecondComponent">Second Component</div>
  )
}
```
---

#### /01-frontend/todo-app/src/components/learning-examples/ThirdComponent.jsx

```
import {Component} from 'react'

export default class ThirdComponent extends Component{
  render() {
    return (
      <div className="ThirdComponent">Third Component</div>
    )
  }
}
```
---

#### /01-frontend/todo-app/src/index.css

```css
body {
  padding: 20px;
  margin: 20px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Oxygen',
    'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue',
    sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

code {
  font-family: source-code-pro, Menlo, Monaco, Consolas, 'Courier New',
    monospace;
}
```
---

#### /01-frontend/todo-app/src/index.js

```js
import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
```

# Section 02

## Exploring React Components with Counter Example

### Step 07

- Step 01 - Exploring React Components with Counter Example
- Step 02 - Getting Started with React Application - Counter
- Step 03 - Getting Started with React Application - Counter - 2
- Step 04 - Exploring React State with useState hook - Adding state to Counter
- Step 05 - Exploring React State - What is happening in Background?
- Step 06 - Exploring React Props - Setting Counter increment value
- Step 07 - Creating Multiple Counter Buttons


#### /01-frontend/todo-app/src/App.js Modified

```js
import './App.css';
import Counter from './components/counter/Counter'

function App() {
  return (
    <div className="App">
      <Counter by={1}/>
      <Counter by={2}/>
      <Counter by={5}/>
    </div>
  )
}

//{property1: 'value1', property2: 'value2'}
// function PlayingWithProps(properties) {
  
//   console.log(properties)
//   console.log(properties.property1)
//   console.log(properties.property2)
  
//   return (
//     <div>Props</div>
//   )
// }

function PlayingWithProps( {property1, property2} ) {
  console.log(property1)
  console.log(property2)
  
  return (
    <div>Props</div>
  )
}


export default App;
```
---

#### /01-frontend/todo-app/src/components/counter/Counter.css New

```css
/* const buttonStyle = {
    fontSize:"16px",
    backgroundColor: "#00a5ab",
    width:"100px",
    margin: "10px",
    color: "white",
    padding: "15px",
    borderRadius: "30px"
}; */

.counterButton {
    font-size:16px;
    background-color: #00a5ab;
    width:100px;
    margin:10px;
    color: white;
    padding: 15px;
    border-radius: 30px;
    border-color: black;
    border-width: 5px;
}

.count {
    font-size: 150px;
    padding: 20px;

}

```
---

#### /01-frontend/todo-app/src/components/counter/Counter.jsx New

```
import { useState } from 'react'
import {PropTypes} from 'prop-types'
import './Counter.css'

export default function Counter({by}) {    

    //[0, ƒ]
    //const [firstElt, secondElt] = array
    const [count, setCount] = useState(0)

    function incrementCounterFunction() {
        setCount(count + by)
    }

    function decrementCounterFunction() {
        setCount(count - by)
    }

    return (
        <div className="Counter">
            <span className="count">{count}</span>
            <div>
                <button className="counterButton" 
                        onClick={incrementCounterFunction}
                >+{by}</button>
                <button className="counterButton" 
                        onClick={decrementCounterFunction}
                >-{by}</button>
 
            </div>
        </div>
    )
}

Counter.propTypes = {
    by: PropTypes.number
}

Counter.defaultProps = {
    by: 5
}
```

### Step 08

- Step 08 - Moving React State Up - Setting up Counter and Counter Button

#### /01-frontend/todo-app/src/App.js Modified

```
      <Counter/>
```
---

#### /01-frontend/todo-app/src/components/counter/Counter.css Modified

```
.counterButton {
    font-size:16px;
    background-color: #00a5ab;
    width:100px;
    margin:10px;
    color: white;
    padding: 15px;
    border-radius: 30px;
    border-color: black;
    border-width: 5px;
}

.count {
    font-size: 50px;
    padding: 20px;

}

.totalCount {
    color: #00a5ab;
    font-size: 150px;
}
```
#### /01-frontend/todo-app/src/components/counter/Counter.jsx Modified

```

export default function Counter() {
    
    function incrementCounterParentFunction(by) {
        setCount(count + by)
    }
    
    return (
        <>
            <span className="totalCount">{count}</span>
            <CounterButton by={1}/>
            <CounterButton by={2}/>
            <CounterButton by={5}/>
        </>
    )
}

function CounterButton({by}) {    


    function incrementCounterFunction() {
        setCount(count + by)
    }

    function decrementCounterFunction() {
        setCount(count - by)
    }

}

CounterButton.propTypes = {
    by: PropTypes.number
}

CounterButton.defaultProps = {
    by: 5
}
```

### Step 09

- Step 09 - Moving React State Up - Calling Parent Component Methods

#### /01-frontend/todo-app/src/components/counter/Counter.jsx Modified

```
    function decrementCounterParentFunction(by) {
        setCount(count - by)
    }

    <CounterButton by={1} 
        incrementMethod={incrementCounterParentFunction} 
        decrementMethod={decrementCounterParentFunction}/>
    <CounterButton by={2} 
        incrementMethod={incrementCounterParentFunction}
        decrementMethod={decrementCounterParentFunction}/>
    <CounterButton by={5} 
        incrementMethod={incrementCounterParentFunction}
        decrementMethod={decrementCounterParentFunction}/>

function CounterButton({by, incrementMethod, decrementMethod}) {    

    //[0, ƒ]
    //const [firstElt, secondElt] = array
    const [count, setCount] = useState(0)

    function incrementCounterFunction() {
        setCount(count + by)
        incrementMethod(by)
    }

    function decrementCounterFunction() {
        setCount(count - by)
        decrementMethod(by)
    }
```

### Step 11

- Step 10 - Exploring React Developer Tools
- Step 11 - Adding Reset Button to Counter

#### /01-frontend/todo-app/src/components/counter/Counter.css Modified

```
button {
}

.resetButton {
    background-color: #824f8c;
}
```
#### /01-frontend/todo-app/src/components/counter/Counter.jsx Modified

```
import CounterButton from './CounterButton'

function resetCounter() {
    setCount(0)
}

<button className="resetButton" onClick={resetCounter}>Reset</button>
```

#### /01-frontend/todo-app/src/components/counter/CounterButton.jsx New

```
import { useState } from 'react'

import {PropTypes} from 'prop-types'

export default function CounterButton({by, incrementMethod, decrementMethod}) {    

    //[0, ƒ]
    //const [firstElt, secondElt] = array
    const [count, setCount] = useState(0)

    function incrementCounterFunction() {
        setCount(count + by)
        incrementMethod(by)
    }

    function decrementCounterFunction() {
        setCount(count - by)
        decrementMethod(by)
    }

    return (
        <div className="Counter">
            <div>
                <button className="counterButton" 
                        onClick={incrementCounterFunction}
                >+{by}</button>
                <button className="counterButton" 
                        onClick={decrementCounterFunction}
                >-{by}</button>
 
            </div>
        </div>
    )
}

CounterButton.propTypes = {
    by: PropTypes.number
}

CounterButton.defaultProps = {
    by: 5
}
```
---

### Step 12

- Step 12 - Refactoring React Counter Component

#### /01-frontend/todo-app/src/App.js Modified

```
// function PlayingWithProps( {property1, property2} ) {
//   console.log(property1)
//   console.log(property2)
```

---
#### /01-frontend/todo-app/src/components/counter/CounterButton.jsx Modified

```
export default function CounterButton({by, incrementMethod, decrementMethod}) {
    return (
        <div className="Counter">
            <div>
                <button className="counterButton" 
                        onClick={() => incrementMethod(by)}
                >+{by}</button>
                <button className="counterButton" 
                        onClick={() => decrementMethod(by)}
                >-{by}</button>
 
            </div>
        </div>
    )
}

```

# Section 03

## Building Java Todo Full Stack Application with Spring Boot and React

### Step 02

- Step 01 - Getting Started with React Todo Management App
- Step 02 - Getting Started with Login Component - Todo React App


#### /01-frontend/todo-app/src/App.js Modified

```
import TodoApp from './components/todo/TodoApp'

function App() {
  return (
    <div className="App">
      <TodoApp />
    </div>
  )
}

```

#### /01-frontend/todo-app/src/components/todo/TodoApp.jsx New

```
export default function TodoApp() {
    return (
        <div className="TodoApp">
            <LoginComponent />
            {/* <WelcomeComponent /> */}
        </div>
    )
}

function LoginComponent() {
    return (
        <div className="Login">
            <div className="LoginForm">
                <div>
                    <label>User Name:</label>
                    <input type="text" name="username" />
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" name="password" />
                </div>
                <div>
                    <button type="button" name="login">login</button>
                </div>
            </div>
        </div>
    )
}

function WelcomeComponent() {
    return (
        <div className="Welcome">
            Welcome Component
        </div>
    )
}
```
---

### Step 04

- Step 03 - Improving Login Component Further - Todo React App
- Step 04 - Adding Hardcoded Authentication - Todo React App

#### /01-frontend/todo-app/src/components/todo/TodoApp.css New

```css
label, input, button {
    margin: 10px;
    padding: 5px;
}
```
---

#### /01-frontend/todo-app/src/components/todo/TodoApp.jsx Modified

```
import { useState } from 'react'
import './TodoApp.css'
    
const [username, setUsername] = useState('in28minutes')
const [password, setPassword] = useState('')
const [showSuccessMessage, setShowSuccessMessage] = useState(false)
const [showErrorMessage, setShowErrorMessage] = useState(false)

function handleUsernameChange(event) {
    setUsername(event.target.value)
}

function handlePasswordChange(event) {
    setPassword(event.target.value)
}

function handleSubmit() {
    if(username==='in28minutes' && password==='dummy'){
        console.log('Success')
        setShowSuccessMessage(true)
        setShowErrorMessage(false)
    } else {
        console.log('Failed')
        setShowSuccessMessage(false)
        setShowErrorMessage(true)
    }
}

function SuccessMessageComponent() {

    if(showSuccessMessage) {
        return <div className="successMessage">Authenticated Successfully</div>
    }
    
    return null   
}

function ErrorMessageComponent() {

    if(showErrorMessage) {
        return <div className="errorMessage">Authentication Failed. Please check your credentials.</div>
    }
    
    return null   
}


<SuccessMessageComponent />
<ErrorMessageComponent />
<input type="text" name="username" value={username} onChange={handleUsernameChange}/>
<input type="password" name="password" value={password} onChange={handlePasswordChange}/>
<button type="button" name="login" onClick={handleSubmit}>login</button>
```

### Step 05

- Step 05 - Conditionally Displaying Messages in Login Component - Todo React App


#### /01-frontend/todo-app/src/components/todo/TodoApp.jsx Modified

```
{showSuccessMessage && <div className="successMessage">Authenticated Successfully</div>}
{showErrorMessage && <div className="errorMessage">Authentication Failed. 
                                                Please check your credentials.</div>}
```

### Step 06

- Step 06 - Adding React Router Dom and Routing from Login to Welcome Component

#### /01-frontend/todo-app/package.json Modified

```
    "react-router-dom": "^6.4.3",
```
#### /01-frontend/todo-app/src/components/todo/TodoApp.jsx Modified

```
import {BrowserRouter, Routes, Route, useNavigate} from 'react-router-dom'
<BrowserRouter>
    <Routes>
        <Route path='/' element={ <LoginComponent /> }></Route>
        <Route path='/login' element={ <LoginComponent /> }></Route>
        <Route path='/welcome' element={<WelcomeComponent /> }></Route>
    </Routes>
</BrowserRouter>
            
            
const navigate = useNavigate();
navigate('/welcome')
```
### Step 07

- Step 07 - Adding Error Component to our React App


#### /01-frontend/todo-app/src/components/todo/TodoApp.jsx Modified

```
<Route path='*' element={<ErrorComponent /> }></Route>

function LoginComponent() {
    //Other code  
    return (
        <div className="Login">
            <h1>Time to Login!</h1>

function WelcomeComponent() {
    return (
        <div className="WelcomeComponent">
            <h1>Welcome in28minutes</h1>
            <div>
                Welcome Component
            </div>
        </div>
    )
}

function ErrorComponent() {
    return (
        <div className="ErrorComponent">
            <h1>We are working really hard!</h1>
            <div>
                Apologies for the 404. Reach out to our team at ABC-DEF-GHIJ.
            </div>
        </div>
    )
}

```

### Step 08

- Step 08 - Removing Hard Coding from Welcome Component

#### /01-frontend/todo-app/src/components/todo/TodoApp.jsx Modified

```
import {BrowserRouter, Routes, Route, useNavigate, useParams} from 'react-router-dom'

<Route path='/welcome/:username' element={<WelcomeComponent /> }></Route>

function LoginComponent() {

    function handleSubmit() {
        if(username==='in28minutes' && password==='dummy'){
            //OTHER CODE
            navigate(`/welcome/${username}`)
            //OTHER CODE
        }
        //OTHER CODE
    }
}

function WelcomeComponent() {
  const {username } = useParams()
  console.log(username)
  <h1>Welcome {username}</h1>
  //OTHER CODE
}
```

### Step 09

- Step 09 - Getting Started with React List Todo Component


#### /01-frontend/todo-app/src/components/todo/TodoApp.jsx Modified

```
export default function TodoApp() {
    return (
        <div className="TodoApp">
            <BrowserRouter>
                <Routes>
                    <Route path='/' element={ <LoginComponent /> } />
                    <Route path='/login' element={ <LoginComponent /> } />
                    <Route path='/welcome/:username' element={<WelcomeComponent /> } />
                    <Route path='/todos' element={<ListTodosComponent /> } />

                    <Route path='*' element={<ErrorComponent /> } />

                </Routes>
            </BrowserRouter>


function ListTodosComponent() {
    const todos = [
                    {id: 1, description: 'Learn AWS'},
                    {id: 2, description: 'Learn Full Stack Dev'},
                    {id: 3, description: 'Learn DevOps'},
                ]


    return (
        <div className="ListTodosComponent">
            <h1>Things You Want To Do!</h1>
            <div>
                <table>
                    <thead>
                            <tr>
                                <td>id</td>
                                <td>description</td>
                            </tr>
                    </thead>
                    <tbody>
                    {
                        todos.map(
                            todo => (
                                <tr key={todo.id}>
                                    <td>{todo.id}</td>
                                    <td>{todo.description}</td>
                                </tr>
                            )
                        )
                    }

                    </tbody>

                </table>
            </div>
        </div>
    )
}

```
### Step 10

- Step 10 - Displaying More Todo Details in React List Todo Component


#### /01-frontend/todo-app/src/components/todo/TodoApp.jsx Modified

```
import {BrowserRouter, Routes, Route, useNavigate, useParams, Link} from 'react-router-dom'


function WelcomeComponent() {

    //OLD CODE

    return (
        <div className="WelcomeComponent">
            <h1>Welcome {username}</h1>
            <div>
                Manage your todos - <Link to="/todos">Go here</Link>
            </div>
        </div>
    )
}

function ListTodosComponent() {

    const today = new Date()
    
    const targetDate = new Date(today.getFullYear()+12, today.getMonth(), today.getDay())
    
    const todos = [
                    {id: 1, description: 'Learn AWS', done: false, targetDate:targetDate},
                    {id: 2, description: 'Learn Full Stack Dev', done: false, targetDate:targetDate},
                    {id: 3, description: 'Learn DevOps', done: false, targetDate:targetDate},
                ]


    return (
        <div className="ListTodosComponent">
            <h1>Things You Want To Do!</h1>
            <div>
                <table>
                    <thead>
                            <tr>
                                <td>ID</td>
                                <td>Description</td>
                                <td>Is Done?</td>
                                <td>Target Date</td>
                            </tr>
                    </thead>
                    <tbody>
                    {
                        todos.map(
                            todo => (
                                <tr key={todo.id}>
                                    <td>{todo.id}</td>
                                    <td>{todo.description}</td>
                                    <td>{todo.done.toString()}</td>
                                    <td>{todo.targetDate.toDateString()}</td>
                                </tr>
                            )
                        )
                    }
                    </tbody>

                </table>
            </div>
        </div>
    )
}

```
### Step 11

- Step 11 - Creating React Header, Footer and Logout Components


#### /01-frontend/todo-app/src/components/todo/TodoApp.jsx Modified

```
<HeaderComponent />            
<Route path='/logout' element={<LogoutComponent /> } />                    
<FooterComponent />

function HeaderComponent() {
    return (
        <div className="header">
            Header <hr/>
        </div>
    )
}

function FooterComponent() {
    return (
        <div className="footer">
            <hr/> Footer 
        </div>
    )
}

function LogoutComponent() {
    return (
        <div className="LogoutComponent">
            <h1>You are logged out!</h1>
            <div>
                Thank you for using our App. Come back soon!
            </div>
        </div>
    )
}

```
### Step 12

- Step 12 - Adding Bootstrap to React Front End Application


#### /01-frontend/todo-app/package.json Modified

```
    "bootstrap": "^5.2.2",
```
#### /01-frontend/todo-app/src/components/todo/TodoApp.jsx Modified

```
function ListTodosComponent() {

        <div className="container">
                <table className="table">
```

#### /01-frontend/todo-app/src/index.js Modified

```
import 'bootstrap/dist/css/bootstrap.min.css';
```
---

### Step 13

- Step 13 - Using Bootstrap to Style Todo React Front End Application


#### /01-frontend/todo-app/src/components/todo/TodoApp.css Modified

```
footer {
    bottom: 0;
    position: absolute;
    width: 90%;
    height: 40px;
```
#### /01-frontend/todo-app/src/components/todo/TodoApp.jsx Modified

```
<header className="border-bottom border-light border-5 mb-5 p-2">
    <div className="container">
        <div className="row">
            <nav className="navbar navbar-expand-lg">
                <a className="navbar-brand ms-2 fs-2 fw-bold text-black" href="https://www.in28minutes.com">in28minutes</a>
                <div className="collapse navbar-collapse">
                    <ul className="navbar-nav">
                        <li className="nav-item fs-5"><Link className="nav-link" to="/welcome/in28minutes">Home</Link></li>
                        <li className="nav-item fs-5"><Link className="nav-link" to="/todos">Todos</Link></li>
                    </ul>
                </div>
                <ul className="navbar-nav">
                    <li className="nav-item fs-5"><Link className="nav-link" to="/login">Login</Link></li>
                    <li className="nav-item fs-5"><Link className="nav-link" to="/logout">Logout</Link></li>
                </ul>
            </nav>
</header>
<footer className="footer">
    <div className="container">
        Your Footer
</footer>
```

### Step 14

- Step 14 - Refactoring React Components to Individual JavaScript Modules


#### /01-frontend/todo-app/src/components/todo/ErrorComponent.jsx New

```
function ErrorComponent() {
    return (
        <div className="ErrorComponent">
            <h1>We are working really hard!</h1>
            <div>
                Apologies for the 404. Reach out to our team at ABC-DEF-GHIJ.
            </div>
        </div>
    )
}

export default ErrorComponent
```
---

#### /01-frontend/todo-app/src/components/todo/FooterComponent.jsx New

```
function FooterComponent() {
    return (
        <footer className="footer">
            <div className="container">
                Your Footer
            </div>
        </footer>
    )
}

export default FooterComponent
```
---

#### /01-frontend/todo-app/src/components/todo/HeaderComponent.jsx New

```
import {Link} from 'react-router-dom'

function HeaderComponent() {
    return (
        
        <header className="border-bottom border-light border-5 mb-5 p-2">
            <div className="container">
                <div className="row">
                    <nav className="navbar navbar-expand-lg">
                        <a className="navbar-brand ms-2 fs-2 fw-bold text-black" href="https://www.in28minutes.com">in28minutes</a>
                        <div className="collapse navbar-collapse">
                            <ul className="navbar-nav">
                                <li className="nav-item"><Link className="nav-link" to="/welcome/in28minutes">Home</Link></li>
                                <li className="nav-item"><Link className="nav-link" to="/todos">Todos</Link></li>
                            </ul>
                        </div>
                        <ul className="navbar-nav">
                            <li className="nav-item"><Link className="nav-link" to="/login">Login</Link></li>
                            <li className="nav-item"><Link className="nav-link" to="/logout">Logout</Link></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </header>

    )
}

export default HeaderComponent
```
---

#### /01-frontend/todo-app/src/components/todo/ListTodosComponent.jsx New

```
function ListTodosComponent() {

    const today = new Date()
    
    const targetDate = new Date(today.getFullYear()+12, today.getMonth(), today.getDay())
    
    const todos = [
                    {id: 1, description: 'Learn AWS', done: false, targetDate:targetDate},
                    {id: 2, description: 'Learn Full Stack Dev', done: false, targetDate:targetDate},
                    {id: 3, description: 'Learn DevOps', done: false, targetDate:targetDate},
                ]


    return (
        <div className="container">
            <h1>Things You Want To Do!</h1>
            <div>
                <table className="table">
                    <thead>
                            <tr>
                                <td>ID</td>
                                <td>Description</td>
                                <td>Is Done?</td>
                                <td>Target Date</td>
                            </tr>
                    </thead>
                    <tbody>
                    {
                        todos.map(
                            todo => (
                                <tr key={todo.id}>
                                    <td>{todo.id}</td>
                                    <td>{todo.description}</td>
                                    <td>{todo.done.toString()}</td>
                                    <td>{todo.targetDate.toDateString()}</td>
                                </tr>
                            )
                        )
                    }
                    </tbody>

                </table>
            </div>
        </div>
    )
}

export default ListTodosComponent
```
---

#### /01-frontend/todo-app/src/components/todo/LoginComponent.jsx New

```
import { useState } from 'react'
import {useNavigate} from 'react-router-dom'

function LoginComponent() {

    const [username, setUsername] = useState('in28minutes')

    const [password, setPassword] = useState('')

    const [showSuccessMessage, setShowSuccessMessage] = useState(false)

    const [showErrorMessage, setShowErrorMessage] = useState(false)

    const navigate = useNavigate();

    function handleUsernameChange(event) {
        setUsername(event.target.value)
    }

    function handlePasswordChange(event) {
        setPassword(event.target.value)
    }

    function handleSubmit() {
        if(username==='in28minutes' && password==='dummy'){
            console.log('Success')
            setShowSuccessMessage(true)
            setShowErrorMessage(false)
            navigate(`/welcome/${username}`)
        } else {
            console.log('Failed')
            setShowSuccessMessage(false)
            setShowErrorMessage(true)
        }
    }

    return (
        <div className="Login">
            <h1>Time to Login!</h1>
            {showSuccessMessage && <div className="successMessage">Authenticated Successfully</div>}
            {showErrorMessage && <div className="errorMessage">Authentication Failed. 
                                                            Please check your credentials.</div>}
            <div className="LoginForm">
                <div>
                    <label>User Name:</label>
                    <input type="text" name="username" value={username} onChange={handleUsernameChange}/>
                </div>
                <div>
                    <label>Password:</label>
                    <input type="password" name="password" value={password} onChange={handlePasswordChange}/>
                </div>
                <div>
                    <button type="button" name="login" onClick={handleSubmit}>login</button>
                </div>
            </div>
        </div>
    )
}

export default LoginComponent
```
---

#### /01-frontend/todo-app/src/components/todo/LogoutComponent.jsx New

```
function LogoutComponent() {
    return (
        <div className="LogoutComponent">
            <h1>You are logged out!</h1>
            <div>
                Thank you for using our App. Come back soon!
            </div>
        </div>
    )
}

export default LogoutComponent
```
---

#### /01-frontend/todo-app/src/components/todo/TodoApp.jsx Modified

```
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import LogoutComponent from './LogoutComponent'
import HeaderComponent from './HeaderComponent'
import ListTodosComponent from './ListTodosComponent'
import ErrorComponent from './ErrorComponent'
import WelcomeComponent from './WelcomeComponent'
import LoginComponent from './LoginComponent'
```
#### /01-frontend/todo-app/src/components/todo/WelcomeComponent.jsx New

```
import {useParams, Link} from 'react-router-dom'

function WelcomeComponent() {

    const {username } = useParams()

    console.log(username)

    return (
        <div className="WelcomeComponent">
            <h1>Welcome {username}</h1>
            <div>
                Manage your todos - <Link to="/todos">Go here</Link>
            </div>
        </div>
    )
}

export default WelcomeComponent
```
---

### Step 16

- Step 15 - Sharing React State with Multiple Components with Auth Context
- Step 16 - Updating React State and Verifying Updates through Auth Context


#### /01-frontend/todo-app/src/components/todo/FooterComponent.jsx Modified

```
import { useContext } from "react";
import { AuthContext } from "./security/AuthContext";
    
const authContext = useContext(AuthContext)
console.log(`Footer component - ${authContext.number}`);
```
#### /01-frontend/todo-app/src/components/todo/HeaderComponent.jsx Modified

```
import { useAuth } from './security/AuthContext'


//const authContext = useContext(AuthContext)
const authContext = useAuth()
//console.log(authContext.number);
    
```
#### /01-frontend/todo-app/src/components/todo/TodoApp.jsx Modified

```
import AuthProvider from './security/AuthContext'
<AuthProvider>
    <!-- All other nodes are child elements -->
</AuthProvider>
```

#### /01-frontend/todo-app/src/components/todo/security/AuthContext.js New

```js
import { createContext, useContext, useState } from "react";

//1: Create a Context
export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

//2: Share the created context with other components
export default function AuthProvider({ children }) {

    //Put some state in the context
    const [number, setNumber] = useState(10)

    setInterval( () => setNumber(number+1), 10000)

    return (
        <AuthContext.Provider value={ {number} }>
            {children}
        </AuthContext.Provider>
    )
} 

```
---

### Step 17

- Step 17 - Setting isAuthenticated into React State - Auth Context


#### /01-frontend/todo-app/src/components/todo/HeaderComponent.jsx Modified

```
function HeaderComponent() {

    //const authContext = useContext(AuthContext)
    const authContext = useAuth()
    const isAuthenticated = authContext.isAuthenticated

    function logout() {
        authContext.setAuthenticated(false)
    }


<li className="nav-item">
    {isAuthenticated 
        && <Link className="nav-link" to="/welcome/in28minutes">Home</Link>}
    
</li>

<li className="nav-item">
    {!isAuthenticated &&
        <Link className="nav-link" to="/login">Login</Link> }
</li>
```
#### /01-frontend/todo-app/src/components/todo/LoginComponent.jsx Modified

```
import { useAuth } from './security/AuthContext'

function LoginComponent() {
    const navigate = useNavigate()
    const authContext = useAuth()

    function handleSubmit() {
        if(username==='in28minutes' && password==='dummy'){
            authContext.setAuthenticated(true)
            //Other code
        } else {
            authContext.setAuthenticated(false)

```
#### /01-frontend/todo-app/src/components/todo/security/AuthContext.js Modified

```
import { createContext, useContext, useState } from "react";

//1: Create a Context
export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

//2: Share the created context with other components
export default function AuthProvider({ children }) {

    //Put some state in the context
    const [number, setNumber] = useState(10)

    const [isAuthenticated, setAuthenticated] = useState(false)

    //setInterval( () => setNumber(number+1), 10000)

    //const valueToBeShared = {number, isAuthenticated, setAuthenticated}

    return (
        <AuthContext.Provider value={ {number, isAuthenticated, setAuthenticated}  }>
            {children}
        </AuthContext.Provider>
    )
}
```
---

### Step 18

- Step 18 - Protecting Secure React Routes using Authenticated Route - 1


#### /01-frontend/todo-app/src/components/todo/HeaderComponent.jsx Modified

```
function HeaderComponent() {

    const authContext = useAuth()
    const isAuthenticated = authContext.isAuthenticated

    function logout() {
        authContext.logout()
    }

```
#### /01-frontend/todo-app/src/components/todo/LoginComponent.jsx Modified

```
function LoginComponent() {


    function handleSubmit() {
        if(authContext.login(username, password)){ //NEW
            navigate(`/welcome/${username}`)
        } else {
            setShowErrorMessage(true)
        }

```
#### /01-frontend/todo-app/src/components/todo/security/AuthContext.js Modified

```
    function login(username, password) {
        if(username==='in28minutes' && password==='dummy'){
            setAuthenticated(true)
            return true            
        } else {
            setAuthenticated(false)
            return false
        }        
    }
    function logout() {
        setAuthenticated(false)
    }
    
    <AuthContext.Provider value={ {isAuthenticated, login, logout}  }>
```

---

### Step 19

- Step 19 - Protecting Secure React Routes using Authenticated Route - 2


#### /01-frontend/todo-app/src/components/todo/TodoApp.jsx Modified

```
import {BrowserRouter, Routes, Route, Navigate} from 'react-router-dom'
import AuthProvider, { useAuth } from './security/AuthContext'

function AuthenticatedRoute({children}) {
    const authContext = useAuth()
    
    if(authContext.isAuthenticated)
        return children

    return <Navigate to="/" />
}

<Route path='/welcome/:username' element={
    <AuthenticatedRoute>
        <WelcomeComponent />
    </AuthenticatedRoute> 
} />

<Route path='/todos' element={
    <AuthenticatedRoute>
        <ListTodosComponent /> 
    </AuthenticatedRoute>
} />

<Route path='/logout' element={
    <AuthenticatedRoute>
        <LogoutComponent /> 
    </AuthenticatedRoute>
} />
```

# Section 04

## Connecting Spring Boot REST API with React Frontend - Java Full Stack Application

### Step 01

- Step 01 - Setting Todo REST API Project for React Full Stack Application


#### /02-rest-api/pom.xml New

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.2.1</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.in28minutes.rest.webservices</groupId>
    <artifactId>restful-web-services</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>restful-web-services</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>21</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
    <repositories>
        <repository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/milestone</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>
    <pluginRepositories>
        <pluginRepository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/milestone</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </pluginRepository>
    </pluginRepositories>

</project>
```
---

#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/RestfulWebServicesApplication.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RestfulWebServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebServicesApplication.class, args);
    }

}
```
---

#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/helloworld/HelloWorldBean.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {

    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean [message=" + message + "]";
    }
}
```
---

#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/helloworld/HelloWorldController.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World v2"; 
    }
    
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean v1"); 
    }
    
    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name)); 
    }   
}
```
---

#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/todo/Todo.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.time.LocalDate;

public class Todo {

    public Todo() {
        
    }
    
    public Todo(int id, String username, String description, LocalDate targetDate, boolean done) {
        super();
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }

    private int id;

    private String username;
    
    private String description;
    private LocalDate targetDate;
    private boolean done;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
                + targetDate + ", done=" + done + "]";
    }

}
```
---

#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/todo/TodoService.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices.todo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
    
    private static List<Todo> todos = new ArrayList<>();
    
    private static int todosCount = 0;
    
    static {
        todos.add(new Todo(++todosCount, "in28minutes","Get AWS Certified", 
                            LocalDate.now().plusYears(10), false ));
        todos.add(new Todo(++todosCount, "in28minutes","Learn DevOps", 
                LocalDate.now().plusYears(11), false ));
        todos.add(new Todo(++todosCount, "in28minutes","Learn Full Stack Development", 
                LocalDate.now().plusYears(12), false ));
    }
    
    public List<Todo> findByUsername(String username){
        Predicate<? super Todo> predicate = 
                todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }
    
    public Todo addTodo(String username, String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++todosCount,username,description,targetDate,done);
        todos.add(todo);
        return todo;
    }
    
    public void deleteById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }
}
```
---

#### /02-rest-api/src/main/resources/application.properties New

```properties
logging.level.org.springframework=info
```
---

#### /02-rest-api/src/test/java/com/in28minutes/rest/webservices/restfulwebservices/RestfulWebServicesApplicationTests.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestfulWebServicesApplicationTests {

    @Test
    void contextLoads() {
    }

}
```

### Step 02

- Step 02 - Calling Spring Boot Hello World REST API from React Hello World Component


#### /01-frontend/todo-app/package.json Modified

```
    "axios": "^1.1.3",
```

#### /01-frontend/todo-app/src/components/todo/WelcomeComponent.jsx Modified

```
import { useState } from 'react'
import { retrieveHelloWorldBean } from './api/HelloWorldApiService'

function WelcomeComponent() {

    const {username } = useParams()

    const [message, setMessage] = useState(null)

    function callHelloWorldRestApi(){
        console.log('called')
        

        axios.get('http://localhost:8080/hello-world')
            .then( (response) => successfulResponse(response) )
            .catch ( (error) => errorResponse(error) )
            .finally ( () => console.log('cleanup') )

    }

    function successfulResponse(response) {
        console.log(response)
        setMessage(response.data)
        //setMessage(response.data.message)
    }

    function errorResponse(error) {
        console.log(error)
    }

    <button className="btn btn-success m-5" onClick={callHelloWorldRestApi}>
        Call Hello World</button>
    
    <div className="text-info">{message}</div>
```


### Step 03

- Step 03 - Enabling CORS Requests for Spring Boot REST API

#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/RestfulWebServicesApplication.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RestfulWebServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebServicesApplication.class, args);
    }
    
    //http://localhost:3000/ to 8080
    //Cross Origin Requests
    //Allow all requests only from http://localhost:3000/
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedMethods("*")
                    .allowedOrigins("http://localhost:3000");
            }
        };
    }

}
```

### Step 05

- Step 04 - Invoking Spring Boot Hello World Bean and Path Param REST API from React
- Step 05 - Refactoring Spring Boot REST API Invocation Code to New Module



#### /01-frontend/todo-app/src/components/todo/WelcomeComponent.jsx Modified

```
import { useState } from 'react'
import { retrieveHelloWorldBean } from './api/HelloWorldApiService'

function WelcomeComponent() {

    const {username } = useParams()

    const [message, setMessage] = useState(null)

    function callHelloWorldRestApi(){
        console.log('called')
        

        // axios.get('http://localhost:8080/hello-world')
        //     .then( (response) => successfulResponse(response) )
        //     .catch ( (error) => errorResponse(error) )
        //     .finally ( () => console.log('cleanup') )

        retrieveHelloWorldBean()
            .then( (response) => successfulResponse(response) )
            .catch ( (error) => errorResponse(error) )
            .finally ( () => console.log('cleanup') )

    }

    function successfulResponse(response) {
        console.log(response)
        //setMessage(response.data)
        setMessage(response.data.message)
    }

    function errorResponse(error) {
        console.log(error)
    }

    <button className="btn btn-success m-5" onClick={callHelloWorldRestApi}>
        Call Hello World</button>
    
    <div className="text-info">{message}</div>
```

#### /01-frontend/todo-app/src/components/todo/api/HelloWorldApiService.js New

```js
import axios from 'axios'

// export function retrieveHelloWorldBean(){
//     return axios.get('http://localhost:8080/hello-world-bean')
// }

export const retrieveHelloWorldBean 
    = () => axios.get('http://localhost:8080/hello-world-bean')
```
---


### Step 06

- Step 06 - Following Axios Best Practices in Spring Boot REST API

#### /01-frontend/todo-app/src/components/todo/WelcomeComponent.jsx Modified

```
import { retrieveHelloWorldPathVariable } from './api/HelloWorldApiService'
              
function callHelloWorldRestApi(){
    console.log('called')
          
    retrieveHelloWorldPathVariable('Ranga')
        .then( (response) => successfulResponse(response) )
        .catch ( (error) => errorResponse(error) )
        .finally ( () => console.log('cleanup') )
```
#### /01-frontend/todo-app/src/components/todo/api/HelloWorldApiService.js Modified

```
import axios from 'axios'

// export function retrieveHelloWorldBean(){
//     return axios.get('http://localhost:8080/hello-world-bean')
// }

const apiClient = axios.create(
    {
        baseURL: 'http://localhost:8080'
    }
);

export const retrieveHelloWorldBean 
    = () => apiClient.get('/hello-world-bean')

export const retrieveHelloWorldPathVariable
    = (username) => apiClient.get(`/hello-world/path-variable/${username}`)

```

---

### Step 09

- Step 07 - Creating Retrieve Todos Spring Boot REST API Get Method
- Step 08 - Displaying Todos from Spring Boot REST API in React App
- Step 09 - Creating Retrieve Todo and Delete Todo Spring Boot REST API Methods


#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/todo/TodoResource.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoResource {
    
    private TodoService todoService;
    
    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }
    
    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username) {
        return todoService.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username,
            @PathVariable int id) {
        return todoService.findById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username,
            @PathVariable int id) {
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
```

#### /01-frontend/todo-app/src/components/todo/ListTodosComponent.jsx Modified

```

import { useEffect, useState } from "react"
import { retrieveAllTodosForUsername } from "./api/TodoApiService"

function ListTodosComponent() {

    const today = new Date()
    
    const targetDate = new Date(today.getFullYear()+12, today.getMonth(), today.getDay())

    const [todos,setTodos] = useState([])
    
    useEffect ( () => refreshTodos(), [])

    function refreshTodos() {
        
        retrieveAllTodosForUsername('in28minutes')
        .then(response => {
            setTodos(response.data)
        }
            
        )
        .catch(error => console.log(error))
    
    }

    {/* <td>{todo.targetDate.toDateString()}</td> */}
    <td>{todo.targetDate.toString()}</td>
```
#### /01-frontend/todo-app/src/components/todo/api/TodoApiService.js New

```js
import axios from 'axios'

const apiClient = axios.create(
    {
        baseURL: 'http://localhost:8080'
    }
);

export const retrieveAllTodosForUsername
    = (username) => apiClient.get(`/users/${username}/todos`)
    //http://localhost:8080/users/in28minutes/todos
```
---

---

### Step 10

- Step 10 - Adding Delete Feature to React Frontend

#### /01-frontend/todo-app/src/components/todo/ListTodosComponent.jsx Modified

```
import { retrieveAllTodosForUsernameApi, deleteTodoApi } from "./api/TodoApiService"


function ListTodosComponent() {

    const today = new Date()
    
    const targetDate = new Date(today.getFullYear()+12, today.getMonth(), today.getDay())

    const [todos,setTodos] = useState([])

    const [message,setMessage] = useState(null)
    
    useEffect ( () => refreshTodos(), [])

    function refreshTodos() {
        
        retrieveAllTodosForUsernameApi('in28minutes')


    function deleteTodo(id) {
        console.log('clicked ' + id)
        deleteTodoApi('in28minutes', id)
        .then(

            () => {
                setMessage(`Delete of todo with id = ${id} successful`)
                refreshTodos()
            }
            //1: Display message
            //2: Update Todos list
        )
        .catch(error => console.log(error))
    }

    //1: Display message
    //2: Update Todos list
    {message && <div className="alert alert-warning">{message}</div>}

      <th>Description</th>
      <th>Is Done?</th>
      <th>Target Date</th>
      <th>Delete</th>

      <td> <button className="btn btn-warning" 
                      onClick={() => deleteTodo(todo.id)}>Delete</button> </td>
```
#### /01-frontend/todo-app/src/components/todo/api/TodoApiService.js Modified

```
export const retrieveAllTodosForUsernameApi
    = (username) => apiClient.get(`/users/${username}/todos`)
    //http://localhost:8080/users/in28minutes/todos

export const deleteTodoApi
    = (username, id) => apiClient.delete(`/users/${username}/todos/${id}`)
    //http://localhost:8080/users/in28minutes/todos
```

---

### Step 11

- Step 11 - Setting Username into React Auth Context

#### /01-frontend/todo-app/src/components/todo/ListTodosComponent.jsx Modified

```
import { useAuth } from "./security/AuthContext"

function ListTodosComponent() {

    const today = new Date()

    const authContext = useAuth()

    const username = authContext.username

    function refreshTodos() {
        retrieveAllTodosForUsernameApi(username)

    function deleteTodo(id) {
        console.log('clicked ' + id)
        deleteTodoApi(username, id)
```
#### /01-frontend/todo-app/src/components/todo/security/AuthContext.js Modified

```
export default function AuthProvider({ children }) {

    //3: Put some state in the context
    const [isAuthenticated, setAuthenticated] = useState(false)

    const [username, setUsername] = useState(null) //NEW

    function login(username, password) {
        if(username==='in28minutes' && password==='dummy'){
            setAuthenticated(true)
            setUsername(username) //NEW
            return true            
        } else {
            setAuthenticated(false)
            setUsername(null) //NEW
            return false
        }        

<AuthContext.Provider value={ {isAuthenticated, login, logout, username}  }>
```
---

### Step 12

- Step 12 - Creating Todo React Component to display Todo Page

#### /01-frontend/todo-app/src/components/todo/ListTodosComponent.jsx Modified

```
import {useNavigate} from 'react-router-dom'

function LoginComponent() {

    const navigate = useNavigate()

    function updateTodo(id) {
        navigate(`/todo/${id}`)


<th>Update</th>

<td> <button className="btn btn-success" 
                onClick={() => updateTodo(todo.id)}>Update</button> </td>
```
#### /01-frontend/todo-app/src/components/todo/TodoApp.jsx Modified

```
import TodoComponent from './TodoComponent'
<Route path='/todo/:id' element={
                                <TodoComponent /> 
  
```
#### /01-frontend/todo-app/src/components/todo/TodoComponent.jsx New

```
import { useEffect, useState } from 'react'
import {useParams} from 'react-router-dom'
import { retrieveTodoApi } from './api/TodoApiService'
import { useAuth } from './security/AuthContext'

export default function TodoComponent() {
    
    const {id} = useParams()
    
    const[description, setDescription] = useState('')

    const authContext = useAuth()
    
    const username = authContext.username
    
    useEffect(
        () => retrieveTodos(),
        [id]
        )

    function retrieveTodos(){
        
        retrieveTodoApi(username, id)
        .then(response => {
            setDescription(response.data.description)

        })
        .catch(error => console.log(error))

    }
    
    return (
        <div className="container">
            <h1>Enter Todo Details </h1>
            <div>
                description: {description}
            </div>

        </div>
    )
}
```
---

#### /01-frontend/todo-app/src/components/todo/api/TodoApiService.js Modified

```
export const retrieveTodoApi
    = (username, id) => apiClient.get(`/users/${username}/todos/${id}`)
```

---

### Step 14

- Step 13 - Adding Formik and Moment Libraries to Display Todo React Component
- Step 14 - Adding Validation to Todo React Component using Formik

#### /01-frontend/todo-app/package.json Modified

```
"formik": "^2.2.9",
"moment": "^2.29.4",
```
#### /01-frontend/todo-app/src/components/todo/ListTodosComponent.jsx Modified

```
  setMessage(`Delete of todos with id = ${id} successful`)
```
#### /01-frontend/todo-app/src/components/todo/TodoComponent.jsx Modified

```
import { useEffect, useState } from 'react'
import {useParams} from 'react-router-dom'
import { retrieveTodoApi } from './api/TodoApiService'
import { useAuth } from './security/AuthContext'
import {Formik, Form, Field, ErrorMessage} from 'formik'

export default function TodoComponent() {
    
    const {id} = useParams()
    
    const[description, setDescription] = useState('')
    const[targetDate, setTargetDate] = useState('')

    const authContext = useAuth()
    
    const username = authContext.username
    
    useEffect(
        () => retrieveTodos(),
        [id]
        )

    function retrieveTodos(){
        
        retrieveTodoApi(username, id)
        .then(response => {
            setDescription(response.data.description)
            setTargetDate(response.data.targetDate)
        })
        .catch(error => console.log(error))
    }

    function onSubmit(values) {
        console.log(values)
    }

    function validate(values) {
        let errors = {
            // description: 'Enter a valid description',
            // targetDate: 'Enter a valid target date'
        }

        if(values.description.length<5) {
            errors.description = 'Enter atleast 5 characters'
        }

        if(values.targetDate == null) {
            errors.targetDate = 'Enter a target date'
        }

        console.log(values)
        return errors
    }

    return (
        <div className="container">
            <h1>Enter Todo Details </h1>
            <div>
                <Formik initialValues={ { description, targetDate } } 
                    enableReinitialize = {true}
                    onSubmit = {onSubmit}
                    validate = {validate}
                    validateOnChange = {false}
                    validateOnBlur = {false}
                >
                {
                    (props) => (
                        <Form>
                            <ErrorMessage 
                                name="description"
                                component="div"
                                className = "alert alert-warning"
                            />
                            
                            <ErrorMessage 
                                name="targetDate"
                                component="div"
                                className = "alert alert-warning"
                            />

                            <fieldset className="form-group">
                                <label>Description</label>
                                <Field type="text" className="form-control" name="description" />
                            </fieldset>
                            <fieldset className="form-group">
                                <label>Target Date</label>
                                <Field type="date" className="form-control" name="targetDate"/>
                            </fieldset>
                            <div>
                                <button className="btn btn-success m-5" type="submit">Save</button>
                            </div>
                        </Form>
                    )
                }
                </Formik>
            </div>

        </div>
    )
}

```

### Step 15

- Step 15 - Adding Update Todo and Create Todo REST API to Spring Boot Backend API

#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/todo/TodoResource.java Modified

```
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

  @PutMapping("/users/{username}/todos/{id}")
  public Todo updateTodo(@PathVariable String username,
          @PathVariable int id, @RequestBody Todo todo) {
      todoService.updateTodo(todo);
      return todo;
  }

  @PostMapping("/users/{username}/todos")
  public Todo createTodo(@PathVariable String username,
           @RequestBody Todo todo) {
      Todo createdTodo = todoService.addTodo(username, todo.getDescription(), 
              todo.getTargetDate(),todo.isDone() );
      
      return createdTodo;
  }
```
---

### Step 16

- Step 16 - Adding Update Feature to React Frontend

#### /01-frontend/todo-app/src/components/todo/TodoComponent.jsx Modified

```
import {useParams, useNavigate} from 'react-router-dom'
import { retrieveTodoApi, updateTodoApi } from './api/TodoApiService'


    function onSubmit(values) {
        console.log(values)
        const todo = {
            id: id,
            username: username,
            description: values.description,
            targetDate: values.targetDate,
            done: false
        }
        
        console.log(todo)

        updateTodoApi(username, id, todo)
        .then(response => {
            navigate('/todos')
        })
        .catch(error => console.log(error))
    }

```
#### /01-frontend/todo-app/src/components/todo/api/TodoApiService.js Modified

```
export const updateTodoApi
    = (username, id, todo) => apiClient.put(`/users/${username}/todos/${id}`, todo)
```
---

### Step 17

- Step 17 - Adding Create New Todo Feature to React Frontend

#### /01-frontend/todo-app/src/components/todo/ListTodosComponent.jsx Modified

```
    function addNewTodo() {
        navigate(`/todo/-1`)
    }

    <div className="btn btn-success m-5" onClick={addNewTodo}>Add New Todo</div>
```
#### /01-frontend/todo-app/src/components/todo/TodoComponent.jsx Modified

```
import { retrieveTodoApi, updateTodoApi, createTodoApi } from './api/TodoApiService'
import moment from 'moment'

function retrieveTodos(){
    if(id != -1) {

    function onSubmit(values) {
        console.log(values)
        
        const todo = {
            id: id,
            username: username,
            description: values.description,
            targetDate: values.targetDate,
            done: false
        }

        console.log(todo)

        if(id==-1) {
            createTodoApi(username, todo)
            .then(response => {
                navigate('/todos')
            })
            .catch(error => console.log(error))
    
        } else {
            updateTodoApi(username, id, todo)
            .then(response => {
                navigate('/todos')
            })
            .catch(error => console.log(error))
        }
    }


    function validate(values) {
        let errors = {
            // description: 'Enter a valid description',
            // targetDate: 'Enter a valid target date'
        }

        if(values.description.length<5) {
            errors.description = 'Enter atleast 5 characters'
        }

        if(values.targetDate == null || values.targetDate=='' || !moment(values.targetDate).isValid()) {
            errors.targetDate = 'Enter a target date'
        }

```

#### /01-frontend/todo-app/src/components/todo/api/TodoApiService.js Modified

```
export const createTodoApi
    = (username,  todo) => apiClient.post(`/users/${username}/todos`, todo)
```

### Step 18

- Step 18 - Securing Spring Boot REST API with Spring Security


#### /02-rest-api/pom.xml Modified

```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-security</artifactId>
</dependency>

```

#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/basic/BasicAuthenticationSecurityConfiguration.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthenticationSecurityConfiguration {
    
    //Filter chain
    // authenticate all requests
    //basic authentication
    //disabling csrf
    //stateless rest api
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        return 
                http
                    .authorizeHttpRequests(
                        auth -> 
                            auth
                            .anyRequest().authenticated()
                        )
                    .httpBasic(Customizer.withDefaults())
                    .sessionManagement(
                        session -> session.sessionCreationPolicy
                        (SessionCreationPolicy.STATELESS))
                    .csrf().disable()
                    .build();
    }

}
```
---

#### /02-rest-api/src/main/resources/application.properties Modified

```
spring.security.user.name=in28minutes
spring.security.user.password=dummy
```


### Step 19

- Step 19 - Adding Authorization Header in React to Spring Boot REST API calls

#### /01-frontend/todo-app/src/components/todo/api/HelloWorldApiService.js Modified

```
//Response to preflight request doesn't pass access control check => Authorization header
export const retrieveHelloWorldPathVariable
    = (username) => apiClient.get(`/hello-world/path-variable/${username}`,{
        headers: {
            Authorization: 'Basic aW4yOG1pbnV0ZXM6ZHVtbXk='
        }
    })
```

### Step 21

- Step 20 - Configuring Spring Security to allow all Options Requests
- Step 21 - Calling Basic Authentication Service when Logging into React App

#### /01-frontend/todo-app/src/components/todo/api/HelloWorldApiService.js Modified

```
export const executeBasicAuthenticationService
    = (token) => apiClient.get(`/basicauth`,{
        headers: {
            Authorization: token
        }
    })

    
```

#### /01-frontend/todo-app/src/components/todo/security/AuthContext.js Modified

```

import { executeBasicAuthenticationService } from "../api/HelloWorldApiService";

    function login(username, password) {

        const baToken = 'Basic ' + window.btoa( username + ":" + password )

        executeBasicAuthenticationService(baToken)
        .then (response => console.log(response))
        .catch (error => console.log(error))

        setAuthenticated(false)

        // if(username==='in28minutes' && password==='dummy'){
        //     setAuthenticated(true)
        //     setUsername(username)
        //     return true            
        // } else {
        //     setAuthenticated(false)
        //     setUsername(null)
        //     return false
        // }        
    }

```


#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/basic/BasicAuthenticationSecurityConfiguration.java Modified

```java
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //1: Response to preflight request doesn't pass access control check
    //2: basic auth
    return 
        http
          .authorizeHttpRequests(
            auth -> 
              auth
              .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() //NEW
              .anyRequest().authenticated()
            )

```


#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/helloworld/HelloWorldController.java Modified

```
@RestController
public class HelloWorldController {

  
  @GetMapping(path = "/basicauth")
  public String basicAuthCheck() {
    return "Success"; 
  }
```
---

### Step 22

- Step 22 - Using async and await to invoke Basic Auth API

#### /01-frontend/todo-app/src/components/todo/LoginComponent.jsx Modified

```
//async await
async function handleSubmit() {
    if(await authContext.login(username, password)){
        navigate(`/welcome/${username}`)
    } else {
        setShowErrorMessage(true)
    }
}
```
#### /01-frontend/todo-app/src/components/todo/security/AuthContext.js Modified

```
export default function AuthProvider({ children }) {

    const [token, setToken] = useState(null)


    async function login(username, password) {

        const baToken = 'Basic ' + window.btoa( username + ":" + password )

        try {

            const response = await executeBasicAuthenticationService(baToken)

            if(response.status==200){
                setAuthenticated(true)
                setUsername(username)
                setToken(baToken)
                return true            
            } else {
                logout()
                return false
            }    
        } catch(error) {
            logout()
            return false
        }
    }


    return (
        <AuthContext.Provider value={ {isAuthenticated, login, logout, username, token}  }>
            {children}
        </AuthContext.Provider>
    )
} 
```
---

### Step 23

- Step 23 - Setting Basic Auth Token into Auth Context

#### /01-frontend/todo-app/src/components/todo/WelcomeComponent.jsx Modified

```
import { useAuth } from './security/AuthContext'

const authContext = useAuth()

retrieveHelloWorldPathVariable('Ranga', authContext.token)
```
#### /01-frontend/todo-app/src/components/todo/api/HelloWorldApiService.js Modified

```
//Response to preflight request doesn't pass access control check => Authorization header
export const retrieveHelloWorldPathVariable
    = (username, token) => apiClient.get(`/hello-world/path-variable/${username}`,{
        headers: {
            Authorization: token
        }
    })
    
```
---

### Step 24

- Step 24 - Setting up Axios Interceptor to add Authorization Header

#### /01-frontend/todo-app/src/components/todo/api/ApiClient.js New

```js
import axios from 'axios'

export const apiClient = axios.create(
    {
        baseURL: 'http://localhost:8080'
    }
);
```
---

#### /01-frontend/todo-app/src/components/todo/api/HelloWorldApiService.js Modified

```
import { apiClient } from './ApiClient'

//Response to preflight request doesn't pass access control check => Authorization header
export const retrieveHelloWorldPathVariable
    = (username, token) => apiClient.get(`/hello-world/path-variable/${username}`,
  //    {
  //      headers: {
  //          Authorization: token
  //      }
  //  }
    )

```
---

#### /01-frontend/todo-app/src/components/todo/api/TodoApiService.js Modified

```
import { apiClient } from './ApiClient'
```
---

#### /01-frontend/todo-app/src/components/todo/security/AuthContext.js Modified

```
import { apiClient } from "../api/ApiClient";

//2: Share the created context with other components
export default function AuthProvider({ children }) {


    async function login(username, password) {

        //SOME CODE

        try {

            //SOME CODE

            if(response.status==200){
                //SOME CODE
                apiClient.interceptors.request.use(
                    (config) => {
                        console.log('intercepting and adding a token')
                        config.headers.Authorization = baToken
                        return config
                    }
                )
                
```
---

### Step 25

- Step 24A -  Debugging Problems with Basic Auth and Spring Boot
- Step 25 - Getting Started with JWT and Spring Security

#### /02-rest-api/pom.xml Modified

```
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-configuration-processor</artifactId>
    </dependency>

```
#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/basic/BasicAuthenticationSecurityConfiguration.java Modified

Disable Basic Authentication Configuration

```
//@Configuration
```

---

#### /02-rest-api/src/main/resources/application.properties Modified

```
#spring.security.user.name=in28minutes
#spring.security.user.password=dummy
```

---

#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/jwt/JwtAuthenticationController.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices.jwt;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {
    
    private final JwtTokenService tokenService;
    
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationController(JwtTokenService tokenService, 
            AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenResponse> generateToken(
            @RequestBody JwtTokenRequest jwtTokenRequest) {
        
        var authenticationToken = 
                new UsernamePasswordAuthenticationToken(
                        jwtTokenRequest.username(), 
                        jwtTokenRequest.password());
        
        var authentication = 
                authenticationManager.authenticate(authenticationToken);
        
        var token = tokenService.generateToken(authentication);
        
        return ResponseEntity.ok(new JwtTokenResponse(token));
    }
}


```
---

#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/jwt/JwtSecurityConfig.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices.jwt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class JwtSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // (1)
                .sessionManagement(
                        session -> 
                            session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS)) // (2)
                .authorizeRequests(
                        auth -> 
                            auth.mvcMatchers("/authenticate", "/actuator", "/actuator/*")
                                .permitAll()
                                .antMatchers(HttpMethod.OPTIONS,"/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated()) // (3)
                .oauth2ResourceServer(
                        OAuth2ResourceServerConfigurer::jwt) // (4)
                .exceptionHandling(
                        (ex) -> 
                            ex.authenticationEntryPoint(
                                new BearerTokenAuthenticationEntryPoint())
                              .accessDeniedHandler(
                                new BearerTokenAccessDeniedHandler()))
                .httpBasic(
                        Customizer.withDefaults()) // (5)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService) {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("in28minutes")
                                .password("{noop}dummy")
                                .authorities("read")
                                .roles("USER")
                                .build();

        return new InMemoryUserDetailsManager(user);
    }


    @Bean
    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    JwtDecoder jwtDecoder() throws JOSEException {
        return NimbusJwtDecoder
                .withPublicKey(rsaKey().toRSAPublicKey())
                .build();
    }
    
    @Bean
    public RSAKey rsaKey() {
        
        KeyPair keyPair = keyPair();
        
        return new RSAKey
                .Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey((RSAPrivateKey) keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    @Bean
    public KeyPair keyPair() {
        try {
            var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Unable to generate an RSA Key Pair", e);
        }
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        JWKSet jwkSet = new JWKSet(rsaKey());
        return (((jwkSelector, securityContext) 
                        -> jwkSelector.select(jwkSet)));
    }
    
}


```
---

#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/jwt/JwtTokenRequest.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices.jwt;

public record JwtTokenRequest(String username, String password) {}


```
---

#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/jwt/JwtTokenResponse.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices.jwt;

public record JwtTokenResponse(String token) {}


```
---

#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/jwt/JwtTokenService.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {
    
    private final JwtEncoder jwtEncoder;

    public JwtTokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(Authentication authentication) {

        var scope = authentication
                        .getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                        .issuer("self")
                        .issuedAt(Instant.now())
                        .expiresAt(Instant.now().plus(90, ChronoUnit.MINUTES))
                        .subject(authentication.getName())
                        .claim("scope", scope)
                        .build();

        return this.jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }
}
```

### Step 26

- Step 26 - Integrating Spring Security JWT REST API with React Frontend
- Step 27 - Debugging Problems with JWT Auth and Spring Boot

#### /01-frontend/todo-app/src/components/todo/api/AuthenticationApiService.js New

```js
import { apiClient } from "./ApiClient";

export const executeBasicAuthenticationService
    = (token) => apiClient.get(`/basicauth`
    ,{
        headers: {
            Authorization: token
        }
    }
    )

export const executeJwtAuthenticationService
    = (username, password) => 
        apiClient.post(`/authenticate`,{username,password})
```
---

#### /01-frontend/todo-app/src/components/todo/security/AuthContext.js Modified

```
import { executeJwtAuthenticationService } from "../api/AuthenticationApiService";

export default function AuthProvider({ children }) {

    async function login(username, password) {

        try {

            const response = await executeJwtAuthenticationService(username, password)

            if(response.status==200){
                
                const jwtToken = 'Bearer ' + response.data.token
                
                setAuthenticated(true)
                setUsername(username)
                setToken(jwtToken)

                apiClient.interceptors.request.use(
                    (config) => {
                        console.log('intercepting and adding a token')
                        config.headers.Authorization = jwtToken
                        return config
                    }
                )

                return true            
            } else {
                logout()
                return false
            }    
        } catch(error) {
            logout()
            return false
        }
    }

```
---

# Section 05

## Connecting Java Full Stack Application (Spring Boot and React) with JPA and Hibernate

### Step 04

- Step 01 - Full Stack React and Spring Boot with JPA and Hibernate
- Step 02 - Full Stack React & Spring Boot with JPA & Hibernate - Getting Tables Ready
- Step 03 - Full Stack React & Spring Boot with JPA & Hibernate - Todo CRUD operations
- Step 04 - Full Stack React & Spring Boot with JPA & Hibernate - Add New Todo


#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/todo/Todo.java Modified

```java
package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Todo {

    @Id
    @GeneratedValue
    private Integer id;

    //OLD CODE

}
```
---

#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/todo/TodoJpaResource.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.restfulwebservices.todo.repository.TodoRepository;

@RestController
public class TodoJpaResource {
    
    private TodoService todoService;
    
    private TodoRepository todoRepository;
    
    public TodoJpaResource(TodoService todoService, TodoRepository todoRepository) {
        this.todoService = todoService;
        this.todoRepository = todoRepository;
    }
    
    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username) {
        //return todoService.findByUsername(username);
        return todoRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username,
            @PathVariable int id) {
        //return todoService.findById(id);
        return todoRepository.findById(id).get();
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username,
            @PathVariable int id) {
        //todoService.deleteById(id);
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username,
            @PathVariable int id, @RequestBody Todo todo) {
        //todoService.updateTodo(todo);
        todoRepository.save(todo);
        return todo;
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username,
             @RequestBody Todo todo) {
        todo.setUsername(username);
        todo.setId(null);
        return todoRepository.save(todo);
//      Todo createdTodo = todoService.addTodo(username, todo.getDescription(), 
//              todo.getTargetDate(),todo.isDone() );
        
//      return createdTodo;
    }

}
```


#### /02-rest-api/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/todo/repository/TodoRepository.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.rest.webservices.restfulwebservices.todo.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{
    
    List<Todo> findByUsername(String username);

}
```
---

#### /02-rest-api/src/main/resources/application.properties Modified

```
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
spring.jpa.defer-datasource-initialization=true
spring.main.banner-mode=off
logging.pattern.console= %d{MM-dd HH:mm:ss} - %logger{36} - %msg%n
```
#### /02-rest-api/src/main/resources/data.sql New

```
insert into todo (id, description, done, target_date,username)
values (10001, 'Learn AWS', false, CURRENT_DATE(), 'in28minutes');

insert into todo (id, description, done, target_date,username)
values (10002, 'Get AWS Certified', false, CURRENT_DATE(), 'in28minutes');

insert into todo (id, description, done, target_date,username)
values (10003, 'Learn DevOps', false, CURRENT_DATE(), 'in28minutes');
```
---

### Step 05

- Step 05 - Full Stack React & Spring Boot with JPA & Hibernate - Connect with MySQL


#### Launch MySQL using Docker

```
docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=todos-user --env MYSQL_PASSWORD=dummytodos --env MYSQL_DATABASE=todos --name mysql --publish 3306:3306 mysql:8-oracle
```

#### /pom.xml Modified

```
<!-- Remove H2
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency> -->


<!-- Use this for Spring Boot 3.1 and higher -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
</dependency> 

<!-- Use this if you are using Spring Boot 3.0 or lower
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency> 
-->

```

#### /src/main/resources/application.properties Modified

```

#comment-h2
#spring.datasource.url=jdbc:h2:mem:testdb
#spring.h2.console.enabled=true

spring.datasource.url=jdbc:mysql://localhost:3306/todos
spring.datasource.username=todos-user
spring.datasource.password=dummytodos
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```
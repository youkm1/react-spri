import logo from './logo.svg';
import './App.css';
import Todo from './Todo';
import AddTodo from './AddTodo';
import React,{ useState, useEffect } from 'react';
import {Container, List,Paper} from "@mui/material";
import { call } from './service/ApiService';

function App() {
  const [items,setItems] = useState([]);

  useEffect(()=>{
    call("/todo","GET",null)
      .then((response)=>setItems(response.data));
    // fetch("http://localhost:8080/todo",requestOptions) //fetch에는 Promise 오브젝트를 반환하기 때문에, onResolve,onReject,onError 콜백 함수를 활용한다.
    //   .then((response)=>response.json())  //onResolve
    //   .then(
    //     (response) => {
    //       setItems(response.data);
    //     },                                //여기선 왜 onReject를 안 쓸까? 왜 복잡하게도 onReject/Error 나눠서 콜백함수를 쓸까? 콜백 지옥과 연관있을까?
    //     (error) => {
          
    //     }                                //onError
    //   );
  },[]);

  const addItem=(item)=>{
    call("/todo","POST",item)
    .then((response)=>setItems(response.data));
  };

  const deleteItem = (item) => {
    call("/todo","DELETE",item)
    .then((response)=>setItems(response.data));
  };

  const editItem = (item) =>{
    call("/todo","PUT",item)
    .then((response)=>setItems(response.data));
  };

  let todoItems =
    items.length > 0 && (
      <Paper style={{margin:16}}>
        <List>
          {items.map((item)=> (
            <Todo 
            item = {item} 
            key = {item.id} 
            editItem={editItem} 
            deleteItem={deleteItem} 
            />
          ))}
        </List>
      </Paper>
    );
  return (<div className='App'>
      <Container maxWidth="md">
        <AddTodo addItem={addItem}/>
        <div className='TodoList'>{todoItems}</div>
      </Container>
  </div>
  );
}

export default App;

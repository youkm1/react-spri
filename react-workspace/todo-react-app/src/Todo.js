import React,{useState} from "react";
import { 
    ListItem,
    ListItemText,
    InputBase,
    Checkbox,
    ListItemSecondaryAction,
    IconButton
} from "@mui/material";
import { DeleteOutlined } from "@mui/icons-material"; 

const Todo = (props) => {
    const [item,setItem] = useState(props.item);
    const [readOnly, setReadOnly] = useState(true); //useState는 안드로이드에서 mutable
    const deleteItem = props.deleteItem;
    const editItem = props.editItem;

    const deleteEventHandler = () => {
        deleteItem(item);
    };
    const editEventHandler = (e)=>{
        setItem({...item, title:e.target.value});
    };
    const turnOffReadOnly = () => {
        setReadOnly(false);
    };
    const checkboxEventHandler = (e)=>{
        item.done = e.target.checked;
        editItem(item);
    };
    const turnOnReadOnly = (e) => {
        if (e.key === "Enter" && readOnly === false){
            setReadOnly(true);
            editItem(item);
        }
    };
    return(
        <ListItem>
            <Checkbox checked = {item.done} onChange={checkboxEventHandler}/>
            <ListItemText>
                <InputBase
                    inputProps={{
                        "aria-label":"naked",
                        readOnly:readOnly
                    }}
                    onClick={turnOffReadOnly}
                    onKeyDown={turnOnReadOnly}
                    onChange={editEventHandler}
                    type="text"
                    id = {item.id}
                    name = {item.id}
                    value={item.title}
                    multiline = {true}
                    fullWidth = {true}
                />
            </ListItemText>
            <ListItemSecondaryAction>
                <IconButton aria-label="Delete Todo"
                    onClick={deleteEventHandler}
                >
                    <DeleteOutlined />
                </IconButton>
            </ListItemSecondaryAction>
        </ListItem> 
    );
};

export default Todo; //adding Todo Component in App Component's render()
import React from 'react'
import List from './List'
export default () => {
  return (
    <div className="searchmain">
    <div className='content-box col25'>Wyszukiwarka</div>
    <div className='content-box col75'>
        <List></List>
    </div>
    </div>
  )
}
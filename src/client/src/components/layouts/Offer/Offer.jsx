import React from 'react'
import  { useState, useEffect } from 'react'

export default props => {
  const [data, setData] = useState(undefined)

  function getOffer(url) {
    fetch(url)
      .then((response) => {
        if(response.ok)
          return response.json();
        else return undefined;
      })
      .then((myJson) => {
        setData(myJson);
      });
  }

  useEffect(() => {
    getOffer(window.location.pathname);
  }, []);

  function goPayment(){
    console.log("tutaj bedzie sie płacić");
  }

  return (
    <div className="content-box flexcolumn search searchmain">
    <div id={props.id} className="content-box" onClick={() => console.log(props.id)}>
      offer screen, go to payment
    </div>
    <div className="flexrow">
        <div className="">
          <img className="pic" alt="" src={data.picture} />
        </div>
        <div className="col75">
          <div className="title">
            <b>{data.name}</b>
          </div>
          <div className="flexrow">
            <div className="marginer price">{data.price} zł</div>
            <div className="marginer">{data.cook}</div>
            <div className="marginer">{data.date}</div>
            <div className="marginer">{data.localization}</div>
          </div>
          <span className="description marginer">{data.description}</span>
        </div>
      </div>
    <button onClick={()=>goPayment()}>Zapłać</button>
    </div>
  )
}

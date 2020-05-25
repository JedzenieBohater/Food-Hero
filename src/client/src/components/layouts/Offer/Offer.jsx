import React, { useRef } from 'react'
import  { useState, useEffect } from 'react'

export default props => {
  const [data, setData] = useState(undefined)
  const [isData,setIsData] = useState(true)
  const [rate, setRate] = useState(0)

  function getOffer(url) {
    fetch(url)
      .then((response) => {
        if(response.ok)
          return response.json();
        else {
          setIsData(false);
         return undefined;
          }
      })
      .then((myJson) => {
        setData(myJson);
      });
  }


  useEffect(() => {
    getOffer("/offers/"+window.location.pathname.split("/").pop());
  }, []);

  const select = useRef();

  function goPayment(){
    console.log("tutaj bedzie sie płacić");
  }

  function Rate(){
    setRate(1);
    setIsData(false);
    var request = new XMLHttpRequest();
    request.open("POST", "/offer_rating/" + window.location.href.split("/").pop() + select.current.value);
    request.send("");
  }

  return (
    
    <div className="content-box flexcolumn search searchmain">
      
      {isData==true ? data? <div>
    <div className="content-box marginer title centering" onClick={() => console.log(props.id)}>
      Szczegóły oferty:
    </div>
    <div className="flexrow">
        <div className="">
          <img className="descpic" alt="" src={data.picture} />
        {rate==0? <div>
        <div className="centering"> Rate it!</div>
        <div className="centering">
          <select id="grade" ref={select}>
            <option value="5">5</option>
            <option value="4">4</option>
            <option value="3">3</option>
            <option value="2">2</option>
            <option value="1">1</option>
          </select>
          <span>★</span>
        </div>
        <button onClick={() => Rate()} className="ratebutton">Send</button></div>
        : <div className="centering">Your grade was sent!</div>}
        </div>
        <div className="col75">
          <div className="title centering">
            <b>{data.name}</b>
          </div>
          <div className="flexrow flexjustify">
            <div className="marginer price">{data.price} zł</div>
            <div className="marginer">{data.firstname + " " + data.lastname}</div>
            <div className="marginer">{data.day}</div>
            <div className="marginer">{data.localization}</div>
          </div>
          <span className="description marginer">{data.description}</span>
        </div>
      </div>
    <button className="ratebutton paybutton" onClick={()=>goPayment()}>Zapłać</button>
    </div>
    : <div/> :
    <div id={props.id} className="content-box marginer title centering" onClick={() => console.log(props.id)}>
      Sorry, this offer is no longer available
    </div>}
    </div>
  )
}

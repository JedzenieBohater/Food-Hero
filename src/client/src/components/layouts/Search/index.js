import React from 'react'
import List from './List'
import data from './exampledata.json'

const offers = data.offers;

export default () => {
  return (
    <div className="searchmain">
    <div className='content-box col25'>Wyszukiwarka</div>
    <div className='content-box col75'>
        {offers.map( offer => (<List
        id={offer.id}
        picture={offer.picture}
        title={offer.title} 
        wystawiajacy={offer.wystawiajacy}
        data={offer.data}
        lokalizacja={offer.lokalizacja}
        ocena={offer.ocena}
        opis={offer.opis}
        ></List>))}
    </div>
    </div>
  )
}
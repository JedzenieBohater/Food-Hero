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
        cook={offer.cook}
        date={offer.date}
        location={offer.location}
        grade={offer.grade}
        description={offer.description}
        ></List>))}
    </div>
    </div>
  )
}